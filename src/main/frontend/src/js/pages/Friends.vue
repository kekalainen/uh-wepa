<template>
    <div>
        <Header title="Friends" />
        <Container>
            <div v-if="!loading">
                <div class="flex flex-wrap" v-if="friends.length > 0">
                    <ProfileCard
                        class="p-2"
                        v-for="user in friends" :key="user.id"
                        :title="user.name" :href="`/profiles/${user.slug}`"
                        :src="user.avatar ? `/api/users/${user.slug}/photos/${user.avatar.id}/square` : '/img/profile.svg'"
                    />
                </div>
                <p v-else>
                    <router-link class="items-center" :to="{ name: 'people' }">
                    <Button>Find friends</Button></router-link>
                </p>
            </div>
            <LoadingSpinner v-else />
            <div class="mt-5" v-if="pending.length > 0">
                <h2>Friend requests</h2>
                <div class="flex flex-wrap">
                    <ProfileCard
                        class="p-2"
                        v-for="user in pending" :key="user.id"
                        :title="user.name" :href="`/profiles/${user.slug}`"
                        :src="user.avatar ? `/api/users/${user.slug}/photos/${user.avatar.id}/square` : '/img/profile.svg'"
                    />
                </div>
            </div>
        </Container>
    </div>
</template>

<script>
export default {
    data: function() {
        return {
            loading: true,
            name: '',
            friends: [],
            pending: []
        }
    },
    methods: {
        loadFriends: function() {
            this.loading = true;
            wretch(`/api/friends?size=16&sort=id,DESC`).get().json(json => {
                this.friends = json.content;
                this.loading = false;
            });
        },
        loadPendingFriends: function() {
            this.loading = true;
            wretch(`/api/friends/pending?size=16&sort=id,DESC`).get().json(json => {
                this.pending = json.content;
                this.loading = false;
            });
        }
    },
    beforeMount: function() {
        this.loadFriends();
        this.loadPendingFriends();
    }
}
</script>
