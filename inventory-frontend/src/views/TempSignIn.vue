<template>
  <div>
    <h1>Temp Sign In</h1>
    <div class="mt-3">
      <b-button
        class="mr-2"
        v-for="user in users"
        :key="user.accessToken"
        @click="login(user)"
        >Login as {{ user.type }}</b-button
      >
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      users: [
        {
          type: "User",
          accessToken: "EgovWorkshop2020",
        },
        {
          type: "Admin",
          accessToken: "EgovWorkshop2020Admin",
        },
      ],
    };
  },
  methods: {
    login(user) {
      const url = new URL(window.location);
      url.pathname = "/";
      url.searchParams.append("accessToken", user.accessToken);
      window.location = url.href;
    },
  },
};
</script>