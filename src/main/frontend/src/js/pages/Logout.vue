<template>
    <Container>
        Logging out.
    </Container>
</template>

<script>
export default {
    beforeMount: function() {
        wretch('/api/users/logout').post().res(_ => {
            this.$router.replace('/login');
            window.location.reload();
        });
    }
}
</script>
