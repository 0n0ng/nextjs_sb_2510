"use client";

import { useParams, useRouter } from "next/navigation";
import { useState, useEffect } from "react";
import api from "@/app/utils/api";

export default function ArticleEdit() {
  const params = useParams();
  const router = useRouter();
  const [article, setArticle] = useState({ subject: "", content: "" });

  useEffect(() => {
    fetchArticle();
  }, []);

  const fetchArticle = () => {
    api
      .get("/articles")
      .then((response) => setArticle(response.data.data.articles))
      .catch((err) => console.log(err));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    await api
    .patch(`/articles/${params.id}`, article)
    .then(function(res) {
      alert("success");
      router.push(`/atricle/${params.id}`)
    })
    .catch(function(err) {
      alert("fail");
    })
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setArticle({ ...article, [name]: value });
  };

  return (
    <>
      <h4>게시물 수정</h4>
      <form onSubmit={handleSubmit}>
        <label>
          제목 :
          <input
            type="text"
            name="subject"
            onChange={handleChange}
            value={article.subject}
          />
        </label>
        <br />
        <label>
          내용 :
          <input
            type="text"
            name="content"
            onChange={handleChange}
            value={article.content}
          />
        </label>
        <input type="submit" value="수정" />
      </form>
    </>
  );
}