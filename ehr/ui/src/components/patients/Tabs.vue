<script lang="ts">
import { defineComponent, ref } from "vue";
import ActionSteps from "@/components/patients/ActionSteps.vue";

export default defineComponent({
	name: "Tabs",
	components: {
		ActionSteps
	},
	setup() {
		const activeTab = ref<string>("actionSteps");

		return {
			activeTab
		};
	}
});
</script>

<template>
	<el-tabs v-model="activeTab">
		<el-tab-pane
			label="Health Concerns"
			name="healthConcerns"
		>
			Health Concerns
		</el-tab-pane>
		<el-tab-pane
			label="Problems"
			name="problems"
		>
			Problems
		</el-tab-pane>
		<el-tab-pane
			label="Goals"
			name="goals"
		>
			Goals
		</el-tab-pane>
		<el-tab-pane
			label="Action Steps"
			name="actionSteps"
		>
			<ActionSteps />
		</el-tab-pane>
		<el-tab-pane
			label="Social Risk Assessments"
			name="socialRiskAssessments"
		>
			Social Risk Assessments
		</el-tab-pane>
		<el-tab-pane
			label="Consent"
			name="consent"
		>
			Consent
		</el-tab-pane>
		<el-tab-pane
			label="Other..."
			name="other"
		>
			Other
		</el-tab-pane>
	</el-tabs>
</template>

<style lang="scss" scoped>
@import "~@/assets/scss/abstracts/variables";

.el-tabs {
	width: 100%;
	background-color: $global-background;

	::v-deep(.el-tabs__header) {
		margin: 0;
	}

	::v-deep(.el-tabs__nav-wrap) {
		border: 1px solid $global-base-border-color;
		padding: 0 15px;

		&::after {
			height: 0;
		}
	}

	::v-deep(.el-tabs__active-bar) {
		box-sizing: content-box;
		padding: 0 20px;
		margin-left: -20px;
	}

	::v-deep(.el-tab-pane) {
		padding: 20px 0;
		background-color: $aqua-haze;
	}

	::v-deep(.el-tabs__item) {
		font-weight: $global-font-weight-normal;
		padding: 20px 35px;
		height: 60px;
		line-height: 20px;

		&:hover,
		&.is-active {
			color: $global-text-color;
		}

		&.is-top:nth-child(2) {
			padding-left: 20px;
		}
	}
}
</style>
