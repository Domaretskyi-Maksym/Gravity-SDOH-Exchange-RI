<script lang="ts">
import { defineComponent, PropType } from "vue";

export default defineComponent({
	props: {
		userName: {
			type: String as PropType<string | undefined>,
			default: undefined
		}
	}
});
</script>

<template>
	<div class="app-header">
		<div class="cbro">
			<img
				class="gravity-logo"
				src="~@/assets/images/gravity-logo.png"
			>
			<div class="title">
				Coordination Platform
				<span class="secondary">(receiving tasks)</span>
			</div>
		</div>

		<div class="nav-tabs">
			<el-tabs
				active-name="sr"
			>
				<el-tab-pane
					name="sr"
				>
					<template #label>
						<div class="label-wrap">
							Service Requests
						</div>
					</template>
				</el-tab-pane>
				<el-tab-pane
					name="ro"
					:disabled="true"
				>
					<template #label>
						<div class="label-wrap">
							Requesting Organizations
						</div>
					</template>
				</el-tab-pane>
				<el-tab-pane
					name="cbo"
					:disabled="true"
				>
					<template #label>
						<div class="label-wrap">
							Community Based Organizations
						</div>
					</template>
				</el-tab-pane>
			</el-tabs>
		</div>

		<div class="spacer"></div>

		<div class="notifications">
			<!-- TODO: if number > 9 it will break -->
			<!-- <div class="mark">
				1
			</div> -->
		</div>

		<div
			v-if="userName !== undefined"
			class="user"
		>
			<div class="pic">
				<div class="mark"></div>
			</div>
			<el-popover
				placement="bottom"
				:width="140"
				:offset="15"
				trigger="click"
				:append-to-body="false"
			>
				<a
					class="logout"
					href="/logout"
				>Logout</a>
				<template #reference>
					<div class="details">
						<div class="name">
							<!-- TODO: restrict its size somehow -->
							{{ userName }}
						</div>
					</div>
				</template>
			</el-popover>
		</div>
	</div>
</template>

<style lang="scss" scoped>
@import "~@/assets/scss/abstracts/variables";

.app-header {
	height: 80px;
	flex-shrink: 0;
	border: $global-border;
	display: flex;
	align-items: center;
	background-color: $global-background;
	padding-right: 45px;

	.cbro {
		border-right: $global-border;
		padding: 10px 15px 0 45px;
		height: 100%;

		.gravity-logo {
			width: 56px;
		}

		.title {
			margin-top: 4px;
			font-size: $global-font-size;
			font-weight: 500;
			line-height: 16px;

			.secondary {
				color: $grey;
			}
		}
	}

	::v-deep(.el-popper.is-light) {
		text-align: center;
		font-size: $global-font-size;
		font-weight: $global-font-weight-normal;
		cursor: pointer;
	}

	.nav-tabs {
		margin-left: 45px;
		height: 100%;

		::v-deep(.el-tabs) {
			height: 100%;

			.el-tabs__header {
				height: 100%;
				margin: 0;
			}

			.el-tabs__nav-wrap {
				height: 100%;

				&::after {
					display: none;
				}
			}

			.el-tabs__nav-scroll {
				height: 100%;
			}

			.el-tabs__nav {
				height: 100%;
			}

			.el-tabs__active-bar {
				background-color: $global-primary-color;
			}

			.el-tabs__item {
				height: 100%;
				padding: 20px 0 0 0;

				.label-wrap {
					padding: 0 45px;
					height: 40px;
					font-size: $global-font-size;
					font-weight: 400;
					color: $global-text-color;
				}

				&.is-active .label-wrap {
					font-weight: 500;
				}

				&:not(:last-child) .label-wrap {
					border-right: $global-border;
				}
			}
		}
	}

	.spacer {
		flex: 1;
	}

	.notifications {
		width: 20px;
		height: 20px;
		background-image: url("~@/assets/images/bell.svg");
		background-size: contain;
		background-repeat: no-repeat;
		background-position: center;
		position: relative;
		cursor: pointer;

		.mark {
			width: 13px;
			height: 13px;
			border-radius: 50%;
			background-color: #fa3d4c;
			box-shadow: 0 2px 5px #fa3d4c;
			color: $white;
			font-size: $global-small-font-size;
			font-weight: 600;
			line-height: 13px;
			text-align: center;
			position: absolute;
			right: -5px;
			top: -3px;
			user-select: none;
		}
	}

	.user {
		margin-left: 55px;
		display: flex;
		align-items: center;

		.pic {
			width: 30px;
			height: 30px;
			background-color: #f0f0f0;
			border: 1px solid #afafaf;
			border-radius: 50%;
			box-shadow: 0 3px 6px rgba(0, 0, 0, 0.304036);
			position: relative;

			.mark {
				position: absolute;
				right: -1px;
				bottom: -1px;
				width: 10px;
				height: 10px;
				background-color: #50e3c2;
				border: 1px solid $white;
				border-radius: 50%;
			}
		}

		.name {
			margin-left: 15px;
			font-size: $global-font-size;
			font-weight: 700;
			cursor: pointer;
		}
	}
}
</style>
