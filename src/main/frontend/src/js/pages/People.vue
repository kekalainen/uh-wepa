<template>
    <div>
        <Header title="People" />
        <Container>
            <Input class="p-2" placeholder="Search by name" name="name" :icon="['fas', 'search']" v-model="name" @change="load" />
            <div class="flex flex-wrap" v-if="users">
                <ProfileCard
                    class="p-2"
                    v-for="user in users" :key="user.id"
                    :title="user.name" :href="`/profiles/${user.handle}`"
                    :src="user.avatar ? `/api/users/${user.slug}/photos/${user.avatar.id}/square` : '/img/profile.svg'"
                />
            </div>
        </Container>
    </div>
</template>

<script>
export default {
    data: function() {
        return {
            name: "",
            users: null
        }
    },
    methods: {
        load: function() {
            wretch(`/api/users?name=${this.name}&size=16&sort=id,DESC`).get().json(json => this.users = json.content);
        }
    },
    beforeMount: function() {
        this.load();
    }
}
</script>
