import { useEffect } from "react";
function User({ name }) {
  useEffect(() => {
    document.title = name;
  }, [name]);
  return (
    <>
      <h1>Title : {name}</h1>
    </>
  );
}
export default User;
