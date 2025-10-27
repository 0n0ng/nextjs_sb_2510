'use client'

import { useParams } from 'next/navigation'
import Link from 'next/link'
import { useQuery } from '@tanstack/react-query'
import api from '@/src/utils/api'

export default function ArticleDetail() {
  const params = useParams()
  const id = Array.isArray(params.id) ? params.id[0] : params.id

  const getArticle = async () => {
    return await api
      .get(`/articles/${params.id}`)
      .then((res) => res.data.data.article)
  }

  // 로딩, 에러, 데이터 받아왔을 때 각각 useQuery가 처리해줌
  //
  const { isLoading, error, data } = useQuery({
    queryKey: ['article', id],
    queryFn: getArticle,
  })

  if (error) console.log(error)

  if (isLoading) <>Loading....</>

  if (data) {
    return (
      <>
        <h4>게시판 상세 {params.id}번</h4>
        <div>제목 : {data.subject}</div>
        <div>작성자 : {data.author}</div>
        <div>내용 : {data.content}</div>
        <div>작성일 : {data.createdDate}</div>
        <div>수정일 : {data.modifiedDate}</div>
        <Link href={`/article/${params.id}/edit`}>수정</Link>

      </>
    )
  }
}
