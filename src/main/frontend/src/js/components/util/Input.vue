<template>
    <div class="flex flex-col">
        <label :for="name" class="mb-1 text-sm font-medium text-gray-700">{{ label }}</label>
        <div class="relative">
            <div class="inline-flex items-center justify-center absolute left-0 top-0 h-full w-10 text-gray-400" v-if="icon">
                <font-awesome-icon :icon="icon" />
            </div>
            <input
                class="px-4 py-2 w-full text-sm placeholder-gray-500 border border-gray-400 focus:outline-none focus:border-purple-500 rounded-md"
                :class="{ 'pl-10': icon }"
                :type="type" :id="name" :placeholder="placeholder" :value="value" @input="$emit('input', $event.target.value)"
                :required="required" :min="min" :max="max"
            />
        </div>
    </div>
</template>

<script>
export default {
    props: {
        value: String,
        label: String,
        name: String,
        placeholder: String,
        icon: Array,
        required: Boolean,
        min: Number,
        max: Number,
        type: {
            type: String,
            default: 'text'
        }
    }
}
</script>
