<template>
    <Card class="w-full md:w-1/2 lg:w-1/4">
        <component :is="href ? 'router-link' : 'div'" :to="href">
            <div class="flex flex-col items-center">
                <img class="w-1/3 mb-2 rounded-full" :src="src" alt="" v-if="src">
                <p class="text-3xl text-gray-900" v-if="title">{{ title }}</p>
            </div>
        </component>
    </Card>
</template>

<script>
export default {
    props: {
        title: String,
        href: String,
        src: String
    }
}
</script>
