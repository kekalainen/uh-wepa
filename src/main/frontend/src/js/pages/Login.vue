<template>
    <div>
        <Container class="min-w-full flex flex-col items-center justify-center min-h-screen bg-gray-200">
            <div class="flex flex-col w-10/12 md:w-4/12 px-4 sm:px-6 md:px-8 lg:px-10 py-8 bg-white shadow-md rounded-md">
                <h1 class="self-center">Welcome</h1>
                <form class="my-6" @submit.prevent="submit">
                    <Input :required="true" class="mb-3" label="Handle" placeholder="Handle" name="handle" :icon="['fas', 'at']" v-model="handle" />
                    <Input :required="true" class="mb-3" label="Name" placeholder="Name" name="name" :icon="['fas', 'address-card']" v-model="name" v-if="signUp" />
                    <Input :required="true" class="mb-3" label="Profile slug" placeholder="Profile slug" name="slug" :icon="['fas', 'user']" v-model="slug" v-if="signUp" />
                    <Input :required="true" class="mb-6" label="Password" placeholder="Password" name="password" :icon="['fas', 'key']" type="password" v-model="password" />
                    <Button class="w-full">{{ signUp ? 'Sign up': 'Log in' }}</Button>
                </form>
                <IconLink @click="signUp = true" :icon="['fas', 'user']" v-if="!signUp">Need an account?</IconLink>
                <IconLink @click="signUp = false" :icon="['fas', 'user']" v-else>Trying to log in?</IconLink>
            </div>
        </Container>
    </div>
</template>

<script>
export default {
    data: function() {
        return {
            auth: globalThis.auth,
            signUp: false,
            handle: null,
            name: null,
            slug: null,
            password: null
        }
    },
    methods: {
        submit: function() {
            if (this.signUp) {
                wretch('/api/users/signup').post({handle: this.handle, name: this.name, slug: this.slug, password: this.password}).json(json => {
                    globalThis.auth = json;
                    this.$router.replace('/profiles/' + json.slug)
                });
            } else {
                wretch('/api/users/login').post({handle: this.handle, password: this.password}).json(json => {
                    globalThis.auth = json;
                    this.$router.replace('/profiles/' + json.slug);
                });
            }
        }
    },
    beforeMount: function() {
        if (typeof this.auth !== 'undefined')
            this.$router.replace('/profiles/' + auth.slug);
    }
}
</script>
