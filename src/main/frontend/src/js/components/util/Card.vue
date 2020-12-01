<template>
    <div>
        <div class="p-6 mx-auto bg-white rounded-md shadow-md hover:shadow-lg">
            <slot />
        </div>
    </div>
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
