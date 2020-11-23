<template>
    <div>
        <Container class="flex content-center">
            <div class="flex items-center">
                <img class="h-24 rounded-full mr-4" :src="user.avatar ? user.avatar : '/img/profile.svg'" alt="">
                <h1>{{ user.name ? user.name : $route.params.slug }}</h1>
            </div>
        </Container>
    </div>
</template>

<script>
export default {
    data() {
        return {
            user: {}
        }
    },
    beforeMount: function() {
        wretch(`/api/users/${this.$route.params.slug}`).get().json(json => this.user = json);
    }
}
</script>
