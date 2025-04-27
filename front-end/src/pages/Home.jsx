import { Link } from "react-router-dom";
import CardHome from "../components/UI/CardHome";
const Home = () => {
  return (
    <>
    <main className="container-lg" style={{height:'auto !important'}}>
    <div className="message">
    </div>

      <article style={{height:'auto !important'}}>
  <div className="row" style={{height:'auto !important'}}>
    <div className="col-md-6">
      <div className="mb-5">
        <h1>Practice English with dictation exercises</h1>
        <p className="mt-4">Dictation is a method to learn languages by listening and writing down what you hear. It is a highly effective method!</p>
        <p>This website contains thousands of audio recordings &amp; videos to help English learners practice easily and improve quickly.</p>
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
        <h2 className="homepage__section-title">How practicing dictation will improve your English skills?</h2>
        <p className="mt-4 mb-3" style={{maxWidth: '580px', margin: 'auto' }}>
          When practicing exercises at dailydictation.com, you will go through 4 main steps, all of them are equally important!
        </p>
      </div>
      <CardHome description="Through the exercises, you will have to listen a lot; that's the key to improving your listening skills in any learning method." header="1. Listen to the audio" src="https://res.cloudinary.com/dr22k5qml/image/upload/v1651286044/dailydictation/tl9vx19jevxg7lv5fi1n.png"/>
      <CardHome description="Through the exercises, you will have to listen a lot; that's the key to improving your listening skills in any learning method." header="1. Listen to the audio" src="https://res.cloudinary.com/dr22k5qml/image/upload/v1651286044/dailydictation/tl9vx19jevxg7lv5fi1n.png"/>
      <CardHome description="Through the exercises, you will have to listen a lot; that's the key to improving your listening skills in any learning method." header="1. Listen to the audio" src="https://res.cloudinary.com/dr22k5qml/image/upload/v1651286044/dailydictation/tl9vx19jevxg7lv5fi1n.png"/>
      <CardHome description="Through the exercises, you will have to listen a lot; that's the key to improving your listening skills in any learning method." header="1. Listen to the audio" src="https://res.cloudinary.com/dr22k5qml/image/upload/v1651286044/dailydictation/tl9vx19jevxg7lv5fi1n.png"/>
    </div>
  </div>
</section>
{/* <hr> */}
<section className="pt-5 pb-5">
  <div className="row">
    <div className="col-12 mb-5 text-center">
      <h2 className="homepage__section-title">Available exercises</h2>
      <p className="mt-4" style={{maxWidth: '650px', margin: 'auto'}}>
        Currently, there are more than 1000 exercises on our website, they are divided into different topics. Below is the list of <Link to="/exercises">all the topics</Link> and some exercises for each of them:
      </p>
    </div>
    <div className="col-md-6">
      <div className="card mb-4">
        <div className="card-body">
          <h3>
            <Link to="/exercises/short-stories">Short Stories</Link>
          </h3>
          {/* <hr> */}
          <p>A collection of audio articles introducing culture, people, places, historical events and daily life in English-speaking countries, especially Canada and America.</p>
          <ul>
            <li>
              <Link to="/exercises/short-stories/1-first-snowfall.1/listen-and-type">
                1. First snowfall
              </Link>
            </li>
            <li>
              <Link to="/exercises/short-stories/2-jessicas-first-day-of-school.5/listen-and-type">
                2. Jessica's first day of school
              </Link>
            </li>
            <li>
              <Link to="/exercises/short-stories/3-my-flower-garden.6/listen-and-type">
                3. My flower garden
              </Link>
            </li>
            <li>
              <Link to="/exercises/short-stories/4-going-camping.7/listen-and-type">
                4. Going camping
              </Link>
            </li>
            <li>
              <Link to="/exercises/short-stories/5-my-house.8/listen-and-type">
                5. My house
              </Link>
            </li>
          </ul>
          <Link to="/exercises/short-stories">View all</Link>
        </div>
      </div>
    </div>
    <div className="col-md-6">
      <div className="card mb-4">
        <div className="card-body">
          <h3>
            <Link to="/exercises/english-conversations">Daily Conversations</Link>
          </h3>
          {/* <hr> */}
          <p>Short and fun English conversations in common situations you may experience in daily life.</p>
          <ul>
            <li>
              <Link to="https://dailydictation.com/exercises/english-conversations/1-at-home-1.399/listen-and-type">
                1. At home (1)
              </Link>
            </li>
            <li>
              <Link to="https://dailydictation.com/exercises/english-conversations/2-at-home-2.400/listen-and-type">
                2. At home (2)
              </Link>
            </li>
            <li>
              <Link to="/exercises/english-conversations/3-my-favorite-photographs.401/listen-and-type">
                3. My Favorite Photographs (1)
              </Link>
            </li>
            <li>
              <Link to="https://dailydictation.com/exercises/english-conversations/4-location.402/listen-and-type">
                4. Location
              </Link>
            </li>
            <li>
              <Link to="https://dailydictation.com/exercises/english-conversations/5-location-2.403/listen-and-type">
                5. Location (2)
              </Link>
            </li>
          </ul>
          <Link to="https://dailydictation.com/exercises/english-conversations">View all</Link>
        </div>
      </div>
    </div>
        <div className="col-md-6">
            <div className="card mb-4">
                <div className="card-body">
                    <h3>
                        <Link to="/exercises/toeic">TOEIC Listening</Link>
                    </h3>
                    {/* <hr> */}
                    <p>
                        In this section, there are a lot of conversations and short talks in everyday life and at work. Let's practice and improve your English communication skills!
                    </p>
                    <ul>
                        <li>
                            <Link to="https://dailydictation.com/exercises/toeic/conversation-1.1035/listen-and-type">
                                Conversation 1
                            </Link>
                        </li>
                        <li>
                            <Link to="https://dailydictation.com/exercises/toeic/conversation-2.1037/listen-and-type">
                                Conversation 2
                            </Link>
                        </li>
                        <li>
                            <Link to="https://dailydictation.com/exercises/toeic/conversation-3.1039/listen-and-type">
                                Conversation 3
                            </Link>
                        </li>
                        <li>
                            <Link to="https://dailydictation.com/exercises/toeic/conversation-4.1041/listen-and-type">
                                Conversation 4
                            </Link>
                        </li>
                        <li>
                            <Link to="https://dailydictation.com/exercises/toeic/short-talk-1.1036/listen-and-type">
                                Short Talk 1
                            </Link>
                        </li>
                        <li>
                            <Link to="https://dailydictation.com/exercises/toeic/short-talk-2.1038/listen-and-type">
                                Short Talk 2
                            </Link>
                        </li>
                        <li>
                            <Link to="https://dailydictation.com/exercises/toeic/short-talk-3.1040/listen-and-type">
                                Short Talk 3
                            </Link>
                        </li>
                        <li>
                            <Link to="https://dailydictation.com/exercises/toeic/short-talk-4.1042/listen-and-type">
                                Short Talk 4
                            </Link>
                        </li>
                    </ul>
                    <Link to="/exercises/toeic">View all</Link>
                </div>
            </div>
        </div>
        <div className="col-md-6">
            <div className="card mb-4">
                <div className="card-body">
                    <h3>
                        <Link to="https://dailydictation.com/exercises/youtube">YouTube</Link>
                    </h3>
                    {/* <hr> */}
                    <p>
                        Are you bored with English lessons for students? Let's learn real English from YouTube videos that native speakers watch and enjoy!
                    </p>
                    <ul>
                        <li>
                            <Link to="https://dailydictation.com/exercises/youtube/the-egg-short-story.602/listen-and-type">
                                The Egg - Short Story
                            </Link>
                        </li>
                        <li>
                            <Link to="https://dailydictation.com/exercises/youtube/the-art-of-balancing-stones.656/listen-and-type">
                                The Art of Balancing Stones
                            </Link>
                        </li>
                        <li>
                            <Link to="https://dailydictation.com/exercises/youtube/why-boredom-is-good-for-you.734/listen-and-type">
                                Why Boredom is Good For You
                            </Link>
                        </li>
                        <li>
                            <Link to="https://dailydictation.com/exercises/youtube/why-do-tumbleweeds-tumble.633/listen-and-type">
                                Why Do Tumbleweeds Tumble?
                            </Link>
                        </li>
                        <li>
                            <Link to="https://dailydictation.com/exercises/youtube/wolf-pack-hunts-a-hare.784/listen-and-type">
                                Wolf Pack Hunts A Hare
                            </Link>
                        </li>
                        <li>
                            <Link to="https://dailydictation.com/exercises/youtube/how-can-you-tell-if-a-plant-is-poisonous.796/listen-and-type">
                                How can you tell if a plant is poisonous?
                            </Link>
                        </li>
                        <li>
                            <Link to="https://dailydictation.com/exercises/youtube/leonardo-da-vinci.621/listen-and-type">
                                Leonardo da Vinci
                            </Link>
                        </li>
                        <li>
                            <Link to="https://dailydictation.com/exercises/youtube/how-orange-juice-is-made-in-factories.613/listen-and-type">
                                How Orange Juice Is Made in Factories
                            </Link>
                        </li>
                    </ul>
                    <Link to="https://dailydictation.com/exercises/youtube">View all</Link>
                </div>
            </div>
        </div>
    <div className="col-md-6">
      <div className="card mb-4">
        <div className="card-body">
          <h3>
            <Link to="/exercises/ielts-listening">IELTS Listening</Link>
          </h3>
          {/* <hr> */}
          <p>
            Listening to IELTS recordings will help you learn a lot of vocabulary and expressions about everyday conversations &amp; academic talks. These recordings are mainly in British and Australian accents.
          </p>
          <ul>
            <li>
              <Link to="https://dailydictation.com/exercises/ielts-listening/cam19-test-1-part-1.1383/listen-and-type">
                IELTS CAM 19 - Test 1 - Part 1
              </Link>
            </li>
            <li>
              <Link to="https://dailydictation.com/exercises/ielts-listening/cam19-test-1-part-2.1384/listen-and-type">
                IELTS CAM 19 - Test 1 - Part 2
              </Link>
            </li>
            <li>
              <Link to="https://dailydictation.com/exercises/ielts-listening/cam19-test-1-part-3.1385/listen-and-type">
                IELTS CAM 19 - Test 1 - Part 3
              </Link>
            </li>
            <li>
              <Link to="https://dailydictation.com/exercises/ielts-listening/cam19-test-1-part-4.1386/listen-and-type">
                IELTS CAM 19 - Test 1 - Part 4
              </Link>
            </li>
            <li>
              <Link to="https://dailydictation.com/exercises/ielts-listening/ielts-cambridge-17-test-2-part-1.504/listen-and-type">
                IELTS CAM 19 - Test 2 - Part 1
              </Link>
            </li>
            <li>
              <Link to="https://dailydictation.com/exercises/ielts-listening/cam19-test-2-part-2.1371/listen-and-type">
                IELTS CAM 19 - Test 2 - Part 2
              </Link>
            </li>
            <li>
              <Link to="https://dailydictation.com/exercises/ielts-listening/cam19-test-2-part-3.1372/listen-and-type">
                IELTS CAM 19 - Test 2 - Part 3
              </Link>
            </li>
            <li>
              <Link to="https://dailydictation.com/exercises/ielts-listening/cam19-test-2-part-4.1373/listen-and-type">
                IELTS CAM 19 - Test 2 - Part 4
              </Link>
            </li>
          </ul>
          <Link to="/exercises/ielts-listening">View all</Link>
        </div>
      </div>
    </div>
    <div className="col-md-6">
      <div className="card mb-4">
        <div className="card-body">
          <h3>
            <Link to="/exercises/toefl-listening">TOEFL Listening</Link>
          </h3>
          {/* <hr> */}
          <p>TOEFL listening recordings are academic conversations &amp; lectures, mainly in American English. These recordings will help you to get better preparation if you are planning to study in an English-speaking country, especially the US and Canada.</p>
          <ul>
            <li>
              <Link to="https://dailydictation.com/exercises/toefl-listening/toefl-conversation-1.537/listen-and-type">
                TOEFL Conversation 1
              </Link>
            </li>
            <li>
              <Link to="https://dailydictation.com/exercises/toefl-listening/toefl-lecture-1.538/listen-and-type">
                TOEFL Lecture 1
              </Link>
            </li>
            <li>
              <Link to="https://dailydictation.com/exercises/toefl-listening/toefl-conversation-2.539/listen-and-type">
                TOEFL Conversation 2
              </Link>
            </li>
            <li>
              <Link to="https://dailydictation.com/exercises/toefl-listening/toefl-lecture-2.540/listen-and-type">
                TOEFL Lecture 2
              </Link>
            </li>
            <li>
              <Link to="https://dailydictation.com/exercises/toefl-listening/toefl-conversation-3.546/listen-and-type">
                TOEFL Conversation 3
              </Link>
            </li>
            <li>
              <Link to="https://dailydictation.com/exercises/toefl-listening/toefl-lecture-3.547/listen-and-type">
                TOEFL Lecture 3
              </Link>
            </li>
            <li>
              <Link to="https://dailydictation.com/exercises/toefl-listening/toefl-conversation-4.550/listen-and-type">
                TOEFL Conversation 4
              </Link>
            </li>
            <li>
              <Link to="https://dailydictation.com/exercises/toefl-listening/toefl-conversation-5.555/listen-and-type">
                TOEFL Lecture 4
              </Link>
            </li>
          </ul>
          <Link to="/exercises/toefl-listening">View all</Link>
        </div>
      </div>
    </div>
    <div className="col-md-6">
      <div className="card mb-4">
        <div className="card-body">
          <h3>
            <Link to="/exercises/spelling-names">Spelling Names</Link>
          </h3>
          {/* <hr> */}
          <p>Let's learn and practice the English alphabet by spelling some common English names.</p>
          <ul>
            <li>
              <Link to="/exercises/spelling-names/female-names.324/listen-and-type">
                Female Names
              </Link>
            </li>
            <li>
              <Link to="/exercises/spelling-names/male-names.325/listen-and-type">
                Male Names
              </Link>
            </li>
            <li>
              <Link to="/exercises/spelling-names/last-names.326/listen-and-type">
                Last Names
              </Link>
            </li>
            <li>
              <Link to="/exercises/spelling-names/animal-names.345/listen-and-type">
                Animal names
              </Link>
            </li>
          </ul>
          <Link to="/exercises/spelling-names">View all</Link>
        </div>
      </div>
    </div>
    <div className="col-md-6">
      <div className="card mb-4">
        <div className="card-body">
          <h3>
            <Link to="/exercises/numbers">Numbers</Link>
          </h3>
          {/* <hr> */}
          <p>Let's improve your ability to understand English numbers when they are spoken quickly by an American.</p>
          <ul>
            <li>
              <Link to="/exercises/numbers/phone-numbers.344/listen-and-type">
                Phone numbers
              </Link>
            </li>
            <li>
              <Link to="/exercises/numbers/numbers-1.330/listen-and-type">
                Numbers (1)
              </Link>
            </li>
            <li>
              <Link to="/exercises/numbers/numbers-2.331/listen-and-type">
                Numbers (2)
              </Link>
            </li>
            <li>
              <Link to="/exercises/numbers/numbers-3.332/listen-and-type">
                Numbers (3)
              </Link>
            </li>
            <li>
              <Link to="/exercises/numbers/numbers-4.333/listen-and-type">
                Numbers (4)
              </Link>
            </li>
          </ul>
          <Link to="/exercises/numbers">View all</Link>
        </div>
      </div>
    </div>
  </div>
</section>
{/* <hr> */}
<section className="box-shadow-01">
  <div>
    <div className="row pb-5 pt-5">
      <div className="col-12 mb-5">
        <h2 className="text-center homepage__section-title">Frequently Asked Questions</h2>
      </div>
      <div className="col-md-6">
        <h4>Is this program free?</h4>
        <p>
          Yes, it's 100% FREE!
        </p>
      </div>
      <div className="col-md-6">
        <h4>Is this website for beginners?</h4>
        <p>
          As long as you can understand this page, you're good to go! But it's better if you know basic English pronunciation, if you don't, watch this
          <Link to="https://www.youtube.com/watch?v=fdRmGvmeY1U&amp;list=PLD6B222E02447DC07" target="_blank">YouTube series</Link>.
        </p>
      </div>
      <div className="col-md-6">
        <h4>How long will it take to become fluent with this website?</h4>
        <p>It depends on many things (such as your current level, how many hours you will spend each day). I can only say that your English will improve very quickly with this method.</p>
      </div>
      <div className="col-md-6">
        <h4>Will my speaking skills improve using this method?</h4>
        <p>
          Speaking and listening skills are related together, once you have better listening skills, it's much easier and faster to improve your speaking skills.
          {/* <br> */}
          Also, you can try to read out loud what you hear, your skills will improve really quickly!
        </p>
      </div>
    </div>
  </div>
</section>
  </main>
    </>
  );
};
export default Home;
