let iconCart = document.querySelector("#cart")
let body = document.querySelector("body")
const closeCart = document.querySelector("#cartClose")

iconCart.addEventListener("click", () => {
    body.classList.toggle("showCart")

})

closeCart.addEventListener("click", () => {
    body.classList.remove("showCart")
})

document.addEventListener('DOMContentLoaded', function() {
    // Seleciona todos os botões de adicionar ao carrinho
    const botoesAdicionarCarrinho = document.querySelectorAll('.adcCart');

    // Adiciona o evento de clique a cada botão
    botoesAdicionarCarrinho.forEach(botao => {
        botao.addEventListener('click', function(event) {
            const produtoId = this.getAttribute('data-produto-id'); // Obtém o ID do produto
            adicionarAoCarrinho(produtoId);
        });
    });

    // Função para adicionar produto ao carrinho via AJAX
    function adicionarAoCarrinho(produtoId) {
        fetch(`/api/carrinho/adicionar?produtoId=${produtoId}&quantidade=1`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => response.json())
        .then(data => {
            if (data.mensagem) {
                alert(data.mensagem); // Mostra a mensagem de sucesso ou erro
            } else {
                alert("Erro ao adicionar ao carrinho");
            }
        })
        .catch(error => console.error("Erro ao adicionar produto ao carrinho:", error));
    }
});