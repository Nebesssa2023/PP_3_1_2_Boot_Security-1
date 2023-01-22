$(async function () {

    deleteUser();
});

function deleteUser() {
    const deleteForm = document.forms["formDeleteUser"];
    deleteForm.addEventListener("submit", ev => {
        ev.preventDefault();
        fetch("http://localhost:8088/admin/delete/" + id, {
            method: 'DELETE'})
            .then(() => {
                $('#deleteFormCloseButton').click();
                allUsers();
            })
    })
}
$(document).ready(function() {
    deleteUser();
});