import { Link } from "react-router-dom";
import CardHome from "../components/UI/CardHome";
const Home = () => {
  return (
    <>
      <main className="container-lg" style={{ height: "auto !important" }}>
        <div className="message"></div>

        <article style={{ height: "auto !important" }}>
          <div className="row" style={{ height: "auto !important" }}>
            <div className="col-md-6">
              <div className="mb-5">
                <h1>Practice English with dictation exercises</h1>
                <p className="mt-4">
                  Dictation is a method to learn languages by listening and
                  writing down what you hear. It is a highly effective method!
                </p>
                <p>
                  This website contains thousands of audio recordings &amp;
                  videos to help English learners practice easily and improve
                  quickly.
                </p>
                <div className="mt-4 d-flex align-items-center">
                  <Link to="/exercises" className="btn btn-primary btn-lg me-3">
                    Start Now
                  </Link>
                  <span>It's 100% FREE!</span>
                </div>
              </div>
            </div>
          </div>
        </article>
        {/* <hr> */}
        <section className="pt-5 box-shadow-01">
          <div>
            <div className="row mt-2 pt-2 text-center">
              <div className="col-md-12 mb-5">
                <h2 className="homepage__section-title">
                  How practicing dictation will improve your English skills?
                </h2>
                <p
                  className="mt-4 mb-3"
                  style={{ maxWidth: "580px", margin: "auto" }}
                >
                  When practicing exercises at dailydictation.com, you will go
                  through 4 main steps, all of them are equally important!
                </p>
              </div>
              <CardHome
                description="Through the exercises, you will have to listen a lot; that's the key to improving your listening skills in any learning method."
                header="1. Listen to the audio"
                src="https://res.cloudinary.com/dr22k5qml/image/upload/v1651286044/dailydictation/tl9vx19jevxg7lv5fi1n.png"
              />
              <CardHome
                description="Typing what you hear forces you to focus on every detail which helps you become better at pronunciation, spelling and writing."
                header="2. Type what you hear"
                src="https://res.cloudinary.com/dr22k5qml/image/upload/v1651286044/dailydictation/a446xbtpfmvkmrgqkfzt.png"
              />
              <CardHome
                description="Error correction is important for your listening accuracy and reading comprehension, it's best to learn from mistakes."
                header="3. Check & correct"
                src="https://res.cloudinary.com/dr22k5qml/image/upload/v1651286042/dailydictation/b2vt1bz3zvjmyfn6unsk.svg"
              />
              {/* <CardHome description="After complete a sentence, try to read it out loud, it will greatly improve your pronunciation & speaking skills!." header="4. Listen to the audio" src="https://res.cloudinary.com/dr22k5qml/image/upload/v1651286044/dailydictation/tl9vx19jevxg7lv5fi1n.png"/> */}
            </div>
          </div>
        </section>
        {/* <hr> */}

        {/* <hr> */}
      </main>
    </>
  );
};
export default Home;
