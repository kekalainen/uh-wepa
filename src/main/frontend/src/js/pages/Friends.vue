<template>
    <div>
        <Header title="Friends" />
        <Container>
            <div class="flex flex-wrap" v-if="friends">
                <Card
                    class="p-2"
                    v-for="user in friends" :key="user.id"
                    :title="user.name" :href="`/profiles/${user.handle}`" src="/img/profile.svg"
                />
            </div>
            <div class="mt-5" v-if="pending.length > 0">
                <h2>Friend requests</h2>
                <div class="flex flex-wrap">
                    <Card
                        class="p-2"
                        v-for="user in pending" :key="user.id"
                        :title="user.name" :href="`/profiles/${user.handle}`" src="/img/profile.svg"
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
            name: "",
            friends: null,
            pending: null
        }
    },
    methods: {
        loadFriends: function() {
            wretch(`/api/friends?size=16&sort=id,DESC`).get().json(json => this.friends = json.content);
        },
        loadPendingFriends: function() {
            wretch(`/api/friends/pending?size=16&sort=id,DESC`).get().json(json => this.pending = json.content);
        }
    },
    beforeMount: function() {
        this.loadFriends();
        this.loadPendingFriends();
    }
}
</script>
