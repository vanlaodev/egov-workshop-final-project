<template>
  <b-card :header="$t('error')">
    <h3>{{ $t("msg_permissionDenied") }}</h3>
    <b-button class="mt-3" @click="$router.replace({ path: '/' })">
      <b-icon icon="house-fill" aria-hidden="true"></b-icon>
      {{ $t("backToHomePage") }}</b-button
    >
  </b-card>
</template>