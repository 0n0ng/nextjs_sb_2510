'use client'

import { useParams } from 'next/navigation'
import { useState, useEffect } from 'react'
import Link from 'next/link'
import { useQuery } from '@tanstack/react-query'
import { error } from 'console'
import api from '@/src/utils/api'

export default function ArticleDetail() {
  const params = useParams()
  const [article, setArticle] = useState({})

  const getArticle = async () => {
    return await api
      .get(`/articles/${params.id}`)
      .then((res) => res.data.data.article)
      .catch((err) => console.log(err))
  }

  // 로딩, 에러, 데이터 받아왔을 때 각각 useQuery가 처리해줌
  // 
  const { isLoading, error, data } = useQuery({
    queryKey: ['article'],
    queryFn: getArticle,
  })

  if (error) console.log(error)

  if (isLoading) <>Loading....</>

  if (data) {
    return (
      <>
        <h4>게시판 상세 {params.id}번</h4>
        <div>{data.subject}</div>
        <div>{data.content}</div>
        <div>{data.createdDate}</div>
        <div>{data.modifiedDate}</div>
        <Link href={`/article/${params.id}/edit`}>수정</Link>
      </>
    )
  }
}
