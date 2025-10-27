export default function AboutLayout({
  children,
}: Readonly<{
  children: React.ReactNode
}>) {
  return (
    <>
      <h2>소개페이지 공통</h2>
      {children}
    </>
  )
}
