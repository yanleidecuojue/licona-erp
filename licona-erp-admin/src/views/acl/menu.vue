<template>
  <div class="app-container">
    <el-table :data="menusList" style="width: 100%; margin-top: 30px" border>
      <el-table-column align="center" label="权限id">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="父id">
        <template slot-scope="scope">
          {{ scope.row.pid }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="权限名称">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="权限类型">
        <template slot-scope="scope">
          {{ scope.row.type }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="权限值">
        <template slot-scope="scope">
          {{ scope.row.permissionValue }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="请求接口">
        <template slot-scope="scope">
          {{ scope.row.interfaceUri }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="请求路径">
        <template slot-scope="scope">
          {{ scope.row.path }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="组件路径">
        <template slot-scope="scope">
          {{ scope.row.component }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="图标">
        <template slot-scope="scope">
          {{ scope.row.icon }}
        </template>
      </el-table-column>
    </el-table>
    <div id="warning">菜单由开发人员维护，只可以查看不可以修改</div>
  </div>
</template>
<script>
import { getMenus } from "@/api/permission";

export default {
  data() {
    return {
      menusList: [],
    };
  },
  created() {
    this.getMenus();
  },
  methods: {
    async getMenus() {
      const res = await getMenus();
      this.menusList = res.data;
    },
  },
};
</script>

<style scoped>
#warning {
  margin-top: 3%;
  color: #ee0000;
}
</style>


