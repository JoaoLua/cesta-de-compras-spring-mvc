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
        <button class="bn1" id="adcCart">Adicionar ao carrinho</button>
        <button class="bn1" id="ver-produto">Ver Produto</button>
      `
      const verProdutoBtn = produtoDiv.querySelector('#ver-produto');
      verProdutoBtn.addEventListener('click', () => {
        // Redireciona para detail.html com o ID do produto
        window.location.href = `detail.html?id=${produto.id}`;
      });

      containerProdutos.appendChild(produtoDiv)
    })
  })
    .catch(error => {
    console.error('Erro ao carregar o arquivo JSON:', error);
  });
