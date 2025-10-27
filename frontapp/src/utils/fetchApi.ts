import api from './api'


// article 하나를 가져오는.
const getArticle = async () => {
    return await api
    .get(`/articles/${params.id}`)
    .then ((res) => res.data.data.article)
}

export default getArticle