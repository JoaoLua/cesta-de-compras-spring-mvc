// Função para obter o parâmetro 'id' da URL
function getQueryParam(param) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(param);
  }
  
  document.addEventListener('DOMContentLoaded', () => {
    // Obtem o id do produto da URL
    const produtoId = getQueryParam('id');
  
    // Carregar o arquivo JSON de produtos
    fetch('src/scripts/produtos.json')
      .then(response => response.json())
      .then(data => {
        // Encontra o produto correspondente ao ID
        const produto = data.find(item => item.id === parseInt(produtoId));
  
        // Se o produto for encontrado, exibe suas informações
        if (produto) {
          const produtoContainer = document.querySelector('#produto-container');
          produtoContainer.innerHTML = `

            <img id="img-produto" src="${produto.imagem}" alt="${produto.nome}">
            <div id="descricao-container">
                <div class="descricao">
                    <h2>${produto.nome}</h2>
                    <h4>Descrição</h4>
                    <p>${produto.descricao}</p>
                    <h4>Preço: ${produto.preco.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}</h4>
                </div>
            <div class="botoes">
                <button class="bn1" id="adcCart">Adicionar ao carrinho</button>
            </div>
          `
        } else {
          console.error('Produto não encontrado.');
        }
      })
      .catch(error => {
        console.error('Erro ao carregar o arquivo JSON:', error);
      });
  });