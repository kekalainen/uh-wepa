<template>
    <Container v-if="user">
        <div class="flex flex-wrap justify-between items-center">
            <div class="flex items-center">
                <img class="h-24 rounded-full mr-4" :src="user.avatar ? `/api/users/${user.slug}/photos/${user.avatar.id}/square` : '/img/profile.svg'" alt="">
                <h1>{{ user.name ? user.name : $route.params.slug }}</h1>
            </div>
            <FriendshipManager :user="user" v-model="friendship" v-if="user.id != auth.id" />
        </div>
        <hr class="my-6">
        <router-view :user="user" :friendship="friendship" />
    </Container>
</template>

<script>
export default {
    data() {
        return {
            auth: globalThis.auth,
            user: null,
            friendship: null
        }
    },
    beforeMount: function() {
        wretch(`/api/users/${this.$route.params.slug}`).get().json(json => this.user = json);
    }
}
</script>
