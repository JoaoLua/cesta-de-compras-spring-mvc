function previewImage(event) {
    const file = event.target.files[0];
    const uploadContainer = document.querySelector('.upload-container');
    const label = document.getElementById('uploadLabel');
    
    if (file) {
        const reader = new FileReader();
        
        reader.onload = function(e) {
            // Remover conteúdo do label
            label.style.display = 'none';

            // Criar elemento de imagem e exibir no container
            const img = document.createElement('img');
            img.src = e.target.result;
            uploadContainer.appendChild(img);
        };
        
        reader.readAsDataURL(file);
    }
}