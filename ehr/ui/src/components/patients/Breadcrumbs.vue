<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
	name: "Breadcrumbs"
});
</script>

<template>
	<div class="breadcrumbs">
		<el-breadcrumb separator-class="el-icon-arrow-right">
			<el-breadcrumb-item :to="{ path: '/' }">
				All Patients
			</el-breadcrumb-item>
			<el-breadcrumb-item>
				Patient Dashboard
			</el-breadcrumb-item>
		</el-breadcrumb>
	</div>
</template>

<style lang="scss" scoped>
@import "~@/assets/scss/abstracts/variables";

.breadcrumbs {
	background: $global-background;
	height: 65px;
	width: 100%;
	padding: 25px 35px;
	box-shadow: 0 2px 5px rgba(51, 51, 51, 0.25);
}
</style>
