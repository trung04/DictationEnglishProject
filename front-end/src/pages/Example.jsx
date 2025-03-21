import React, { useState } from "react";
function Example() {
  const [count, setCount] = useState(0);
  return (
    <div>
      <p>
        You clicked <strong>{count}</strong> times
      </p>
      <button onClick={() => setCount(count + 1)}>
        <img
          src="https://tse4.mm.bing.net/th?id=OIP.8JZgsU30EIUv97ey_cF5KgHaI1&pid=Api&P=0&h=180"
          alt=""
        />
      </button>
    </div>
  );
}
export default Example;
