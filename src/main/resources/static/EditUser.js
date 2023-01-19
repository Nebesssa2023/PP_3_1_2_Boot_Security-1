$(async function () {
    editUser();

});

function editUser() {
    const editForm = document.forms["formEditUser"];
    editForm.addEventListener("submit", ev => {
        ev.preventDefault();
        let editUserRoles = [];
        for (let i = 0; i < editForm.roles.options.length; i++) {
            if (editForm.roles.options[i].selected) editUserRoles.push({
                id: editForm.roles.options[i].value,
                role: "ROLE_" + editForm.roles.options[i].text
            })
        }

        fetch("http://localhost:8088/admin/user/" + editForm.id.value, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            , credentials:'same-origin'},
            body: JSON.stringify({
                id: editForm.id.value,
                name: editForm.name.value,
                lastName: editForm.lastName.value,
                age: editForm.age.value,
                username: editForm.username.value,
                password: editForm.password.value,

                roles: editUserRoles
            })
        }).then(() => {
            $('#editFormCloseButton').click();
            allUsers().then((r, form) => {form.reset();
                $('#allUsersTable').click();} );
        })
    })
}