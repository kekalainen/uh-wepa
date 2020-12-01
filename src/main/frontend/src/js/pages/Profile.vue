<template>
    <div>
        <Container v-if="user">
            <div class="flex flex-wrap justify-between items-center">
                <div class="flex items-center">
                    <img class="h-24 rounded-full mr-4" :src="user.avatar ? `/api/users/${user.slug}/photos/${user.avatar.id}/square` : '/img/profile.svg'" alt="">
                    <h1>{{ user.name ? user.name : $route.params.slug }}</h1>
                </div>
                <FriendshipManager :user="user" v-model="friendship" v-if="user.id != auth.id" />
            </div>
            <div>
                <hr class="my-6">
                <div class="flex justify-around">
                    <Button :class="{ 'btn-secondary': section.name != activeSection }" @click="activeSection = section.name" v-for="section in sections" :key="section.name">
                        <font-awesome-icon class="mr-1" :icon="['fas', section.icon]" /> {{ section.title }}
                    </Button>
                </div>
                <hr class="my-6">
                <Posts :user="user" :friendship="friendship" v-if="activeSection == 'posts'" />
                <Photos :user="user" v-if="activeSection == 'photos'" />
            </div>
        </Container>
    </div>
</template>

<script>
export default {
    data() {
        return {
            auth: globalThis.auth,
            user: null,
            friendship: null,
            activeSection: 'posts',
            sections: [
                {
                    name: 'posts',
                    title: 'Posts',
                    icon: 'pen'
                },
                {
                    name: 'photos',
                    title: 'Photos',
                    icon: 'images'
                }
            ]
        }
    },
    beforeMount: function() {
        wretch(`/api/users/${this.$route.params.slug}`).get().json(json => this.user = json);
    }
}
</script>
