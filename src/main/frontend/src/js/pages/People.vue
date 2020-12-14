<template>
    <div>
        <Header title="People" />
        <Container>
            <Input class="p-2" placeholder="Search by name" name="name" :icon="['fas', 'search']" v-model="name" @change="load" />
            <div v-if="!loading">
                <div class="flex flex-wrap" v-if="users.length > 0">
                    <ProfileCard
                        class="p-2"
                        v-for="user in users" :key="user.id"
                        :title="user.name" :href="`/profiles/${user.slug}`"
                        :src="user.avatar ? `/api/users/${user.slug}/photos/${user.avatar.id}/square` : '/img/profile.svg'"
                    />
                </div>
                <p class="text-center" v-else>No users found.</p>
            </div>
            <LoadingSpinner v-else />
        </Container>
    </div>
</template>

<script>
export default {
    data: function() {
        return {
            loading: true,
            name: '',
            users: []
        }
    },
    methods: {
        load: function() {
            this.loading = true;
            wretch(`/api/users?name=${this.name}&size=16&sort=id,DESC`).get().json(json => {
                this.users = json.content;
                this.loading = false;
            });
        }
    },
    beforeMount: function() {
        this.load();
    }
}
</script>
