<template>
  <div class="app-container">
    <el-button type="primary" @click="handleAddUser">添加用户</el-button>

    <el-table :data="usersList" style="width: 100%; margin-top: 30px" border>
      <el-table-column align="center" label="用户id">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="用户名">
        <template slot-scope="scope">
          {{ scope.row.username }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="昵称">
        <template slot-scope="scope">
          {{ scope.row.nickName }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="头像地址">
        <template slot-scope="scope">
          {{ scope.row.avatar }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="邮箱地址">
        <template slot-scope="scope">
          {{ scope.row.avatar }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="电话号码">
        <template slot-scope="scope">
          {{ scope.row.phoneNumber }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="用户状态">
        <template slot-scope="scope">
          {{ scope.row.status }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="创建时间">
        <template slot-scope="scope">
          {{ scope.row.created }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="更新时间">
        <template slot-scope="scope">
          {{ scope.row.updated }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button type="primary" size="small" @click="handleEdit(scope)"
            >编辑</el-button
          >
          <el-button type="danger" size="small" @click="handleDelete(scope)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <el-dialog
      :visible.sync="dialogVisible"
      :title="dialogType === 'edit' ? '编辑用户' : '添加用户'"
    >
      <el-form :model="user" label-width="80px" label-position="left">
        <el-form-item v-if="dialogType === 'new'" label="用户名">
          <el-input v-model="user.username" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item v-if="dialogType === 'edit'" label="用户名">
          <label for="user.username">{{ user.username }}</label>
        </el-form-item>
        <el-form-item v-if="dialogType === 'new'" label="密码">
          <el-input v-model="user.password" placeholder="请输入密码" />
        </el-form-item>
        <el-form-item v-if="dialogType === 'new'" label="确认密码">
          <el-input v-model="user.password" placeholder="请输入确认密码" />
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="user.nickName" placeholder="请输入用户昵称" />
        </el-form-item>
        <el-form-item label="头像地址">
          <el-input v-model="user.avatar" placeholder="请输入用户头像地址" />
        </el-form-item>
        <el-form-item label="邮箱地址">
          <el-input v-model="user.email" placeholder="请输入用户邮箱地址" />
        </el-form-item>
        <el-form-item label="电话号码">
          <el-input
            v-model="user.phoneNumber"
            placeholder="请输入用户电话号码"
          />
        </el-form-item>
        <el-form-item label="角色">
          <el-checkbox-group v-model="checkedRoles">
            <el-checkbox v-for="role in roles" :label="role" :key="role">{{
              role
            }}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
      </el-form>
      <div style="text-align: right">
        <el-button type="danger" @click="dialogVisible = false">关闭</el-button>
        <el-button type="primary" @click="confirmUser">确认</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getUsers, register, deleteUser, updateUser } from "@/api/user";
import { getRoles } from "@/api/role";
import { deepClone } from "@/utils";

const defaultUser = {
  username: "",
  password: "",
  nickName: "",
  avatar: "",
  email: "",
  phoneNumber: "",
};

export default {
  data() {
    return {
      user: Object.assign({}, defaultUser),
      usersList: [],
      dialogVisible: false,
      dialogType: "new",
      checkedRoles: [],
      roles: [],
      updatedUser: {},
    };
  },
  created() {
    this.getUsers();
    this.getRoles();
  },
  methods: {
    async getUsers() {
      const res = await getUsers();
      this.usersList = res.data;
    },

    async getRoles() {
      const res = await getRoles();
      for (let i in res.data) {
        this.roles.push(res.data[i].roleName);
      }
    },
    handleAddUser() {
      this.checkedRoles.length = 0;
      this.checkedRoles.push(null);
      this.user = Object.assign({}, defaultUser);
      this.dialogType = "new";
      this.dialogVisible = true;
    },
    async handleEdit(scope) {
      this.checkedRoles.length = 0;
      const res = await getRoles(scope.row.id);
      if (res.data === null) {
        this.checkedRoles.push(null);
      } else {
        for (let i in res.data) {
          this.checkedRoles.push(res.data[i].roleName);
        }
      }

      this.updatedUser.username = scope.row.username;
      this.updatedUser.nick_name = scope.row.nickName;
      this.updatedUser.avatar = scope.row.avatar;
      this.updatedUser.email = scope.row.email;
      this.updatedUser.phone_number = scope.row.phoneNumber;
      this.dialogType = "edit";
      this.dialogVisible = true;
      this.user = deepClone(scope.row);
    },
    handleDelete(scope) {
      this.$confirm("确定删除此角色?", "警告", {
        confirmButtonText: "确认",
        cancelButtonText: "禁止",
        type: "warning",
      })
        .then(async () => {
          await deleteUser(scope.row.username);
          this.usersList.splice(scope.$index, 1);
          this.$message({
            type: "success",
            message: "Delete succed!",
          });
        })
        .catch((err) => {
          console.error(err);
        });
    },
    async confirmUser() {
      const isEdit = this.dialogType === "edit";

      if (isEdit) {
        this.updatedUser.roles = this.checkedRoles.toString();
        await updateUser(this.updatedUser);
      } else {
        await register(this.user);
        this.usersList.push(this.user);
      }

      const { username } = this.user;
      this.dialogVisible = false;
      this.$notify({
        title: "Success",
        dangerouslyUseHTMLString: true,
        message: `
            <div>username: ${username}</div>
          `,
        type: "success",
      });
    },
  },
};
</script>

<style lang="scss" scoped>
</style>
