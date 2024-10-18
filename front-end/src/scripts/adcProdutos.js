const containerProdutos = document.querySelector('.produto-container')

// Caminho do arquivo JSON
fetch('src/scripts/produtos.json')
  .then(response => response.json())
  .then(produtos => {
    
    produtos.forEach(produto => {

      const produtoDiv = document.createElement('div')
      produtoDiv.classList.add('produto')

      produtoDiv.innerHTML = `
        <img src="${produto.imagem}" alt="${produto.nome}">
        <p>${produto.nome}</p>
        <p>R$ ${produto.preco.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}</p>
        <button class="bn1">Adicionar ao carrinho</button>
        <button class="bn1">Ver Produto</button>
      `

      containerProdutos.appendChild(produtoDiv)
    })
  })
    .catch(error => {
    console.error('Erro ao carregar o arquivo JSON:', error);
  });