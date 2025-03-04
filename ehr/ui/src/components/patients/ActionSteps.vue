<script lang="ts">
import { computed, defineComponent, onMounted, onUnmounted, ref, watch, h } from "vue";
import RequestTable from "@/components/patients/RequestTable.vue";
import NewRequestDialog from "@/components/patients/NewRequestDialog.vue";
import { Comment, Occurrence, Task, Condition, Goal, Procedure, TaskStatus, Coding } from "@/types";
import { TasksModule } from "@/store/modules/tasks";
import { ElNotification } from "element-plus";
import TaskStatusIcon from "@/components/patients/TaskStatusIcon.vue";

export type TableData = {
	name: string,
	status: TaskStatus,
	category: Coding,
	problems: Condition[],
	goals: Goal[],
	performer: string | null | undefined,
	consent: string
	outcomes: string | null,
	comments: Comment[],
	lastModified: string | null,
	request: Coding,
	priority: string | null,
	occurrence: Occurrence,
	procedures: Procedure[],
	id: string,
	statusReason: string | null
}

export type taskStatusDiff = {
	name: string,
	oldStatus: TaskStatus,
	newStatus: TaskStatus
};

export default defineComponent({
	name: "ActionSteps",
	components: {
		RequestTable,
		NewRequestDialog
	},
	setup() {
		const activeGroup = ref<string>("referrals");
		const newRequestDialogVisible = ref<boolean>(false);
		const isRequestLoading = ref<boolean>(false);
		const tasks = computed<Task[]>(() => TasksModule.tasks);
		const tableData = computed<TableData[]>(() =>
			tasks.value.map((task: Task) => ({
				name: task.name,
				status: task.status,
				category: task.serviceRequest.category,
				problems: task.serviceRequest.conditions,
				goals: task.serviceRequest.goals,
				performer: task.organization?.name,
				consent: task.serviceRequest.consent.display,
				outcomes: task.outcome,
				comments: task.comments,
				lastModified: task.lastModified,
				request: task.serviceRequest.code,
				priority: task.priority,
				occurrence: task.serviceRequest.occurrence,
				procedures: task.procedures,
				id: task.id,
				statusReason: task.statusReason
			}))
		);
		const activeRequests = computed<TableData[]>(() => tableData.value.filter(t => t.status !== "Completed"));
		const completedRequests = computed<TableData[]>(() => tableData.value.filter(t => t.status === "Completed"));

		onMounted(async () => {
			isRequestLoading.value = true;
			try {
				await TasksModule.getTasks();
				pollData();
			} finally {
				isRequestLoading.value = false;
			}
		});
		const pollId = ref<number>();
		const pollData = async () => {
			await TasksModule.getTasks();
			pollId.value = window.setTimeout(pollData, 5000);
		};
		onUnmounted(() => {
			clearTimeout(pollId.value);
		});

		//
		// Find diffs in tasks statuses.
		// Skip new tasks, we don't need to show notifications on that, cause we are the only one who creates tasks.
		//
		const findDiff = (val: Task[], oldVal: Task[]): taskStatusDiff[] =>
			val.flatMap((task: Task) => {
				const existingTask = oldVal.find(t => t.id === task.id);
				if (!existingTask) {
					return [];
				}

				const oldStatus = existingTask.status;
				const newStatus = task.status;
				if (oldStatus === newStatus) {
					return [];
				}

				return [{
					name: task.name,
					oldStatus,
					newStatus
				}];
			});

		watch(() => tasks.value, (val, oldVal) => {
			const diff = findDiff(val, oldVal);

			diff.forEach(update => {
				const message = h("p", [
					`CP changed status of task "${update.name}" from `,
					h(TaskStatusIcon, {
						status: update.oldStatus,
						small: true,
						showLabel: true
					}),
					" to ",
					h(TaskStatusIcon,{
						status: update.newStatus,
						small: true,
						showLabel: true
					})
				]);

				ElNotification({
					title: "Notification",
					iconClass: "notification-bell",
					duration: 10000,
					message
				});
			});
		});

		return {
			activeGroup,
			newRequestDialogVisible,
			activeRequests,
			completedRequests,
			isRequestLoading
		};
	}
});
</script>

<template>
	<div class="action-steps">
		<div class="action-steps-switcher">
			<el-radio-group
				v-model="activeGroup"
				size="mini"
			>
				<el-radio-button label="interventions">
					Interventions
				</el-radio-button>
				<el-radio-button label="referrals">
					Referrals
				</el-radio-button>
			</el-radio-group>
			<el-button
				v-if="activeGroup === 'referrals' && (activeRequests.length || completedRequests.length)"
				plain
				round
				type="primary"
				size="mini"
				@click="newRequestDialogVisible = true"
			>
				Add New Request
			</el-button>
		</div>
		<div
			v-if="activeGroup === 'interventions'"
			class="interventions"
		>
			Interventions
		</div>
		<div
			v-else
			v-loading="isRequestLoading"
			class="referrals"
		>
			<RequestTable
				v-if="activeRequests.length"
				:data="activeRequests"
			/>
			<RequestTable
				v-if="completedRequests.length"
				:data="completedRequests"
				title="Completed Requests"
			/>
			<div
				v-if="!isRequestLoading && !(completedRequests.length || activeRequests.length)"
				class="no-request-data"
			>
				<h2>No Referral Requests Yet</h2>
				<el-button
					plain
					round
					type="primary"
					size="mini"
					@click="newRequestDialogVisible = true"
				>
					Add New Request
				</el-button>
			</div>
		</div>

		<NewRequestDialog
			:visible="newRequestDialogVisible"
			@close="newRequestDialogVisible = false"
		/>
	</div>
</template>

<style lang="scss" scoped>
@import "~@/assets/scss/abstracts/variables";

.action-steps-switcher {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 20px;
}

.referrals {
	min-height: 130px;
}

.no-request-data {
	height: 350px;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	background-color: $global-background;

	h2 {
		color: $whisper;
		font-size: $global-xxxlarge-font-size;
		font-weight: $global-font-weight-normal;
		margin-bottom: 50px;
	}
}
</style>
