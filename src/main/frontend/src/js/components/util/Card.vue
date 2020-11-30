<template>
    <component class="w-full md:w-1/2 lg:w-1/4" :is="href ? 'router-link' : 'div'" :to="href">
        <div class="p-6 mx-auto bg-white rounded-md shadow-md hover:shadow-lg">
            <div class="flex flex-col items-center">
                <img class="w-1/3 mb-2 rounded-full" :src="src" alt="" v-if="src">
                <p class="text-3xl text-gray-900">{{ title }}</p>
            </div>
        </div>
    </component>

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
