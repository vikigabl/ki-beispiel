document.addEventListener('DOMContentLoaded', function() {
    const itemForm = document.getElementById('itemForm');

    itemForm.addEventListener('submit', function(e) {
        e.preventDefault();

        const name = document.getElementById('name').value;
        const description = document.getElementById('description').value;

        const item = {
            name: name,
            description: description
        };

        fetch('/api/items', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item)
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            throw new Error('Failed to create item');
        })
        .then(data => {
            location.reload();
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error creating item: ' + error.message);
        });
    });
});

function deleteItem(id) {
    if (confirm('Are you sure you want to delete this item?')) {
        fetch('/api/items/' + id, {
            method: 'DELETE'
        })
        .then(response => {
            if (response.ok) {
                location.reload();
            } else {
                throw new Error('Failed to delete item');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error deleting item: ' + error.message);
        });
    }
}
