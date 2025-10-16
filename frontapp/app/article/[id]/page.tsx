'use client'
import { useParams } from "next/navigation"

export default function() {
    const params = useParams();
    console.log(params);

    return <>게시판 상세 {params.id}</>
}