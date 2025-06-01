import axios from "axios";
import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { Link } from "react-router-dom";
const Practice = () => {
  const { id } = useParams();
  const [cards, setCards] = useState([]);
  const [flashcard, setFlashcard] = useState([]);
  useEffect(() => {
    if (!id) return;
    const fetchData = async () => {
      try {
        const res = await axios.get(
          `http://localhost:8080/api/flashcards/${id}`
        );
        setFlashcard(res.data);
        console.log(res.data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchData();
  }, [id]);
  useEffect(() => {
    const fetch = async () => {
      try {
        const res = await axios.get(
          `http://localhost:8080/api/flashcard-details/by-flashcard/${id}`
        );
        setCards(res.data);
      } catch (error) {
        console.log(error);
      }
    };
    fetch();
  }, [id]);

  const [currentIndex, setCurrentIndex] = useState(0);
  const [flipped, setFlipped] = useState(false);

  const prevCard = () => {
    setFlipped(false);
    setCurrentIndex((prev) => (prev === 0 ? cards.length - 1 : prev - 1));
  };

  const nextCard = () => {
    setFlipped(false);
    setCurrentIndex((prev) => (prev + 1) % cards.length);
  };

  const currentCard = cards[currentIndex];

  return (
    <div className="container py-5 d-flex flex-column align-items-center">
      <h1 className="text-primary fw-bold mb-5">
        <Link
          to={`/flashcard/${flashcard?.id}`}
          className="text-decoration-none"
        >
          📘 Flashcard: {flashcard?.title}
        </Link>
      </h1>

      {currentCard ? (
        <>
          <div
            className="flip-card shadow rounded-4"
            style={{
              width: "360px",
              height: "230px",
              perspective: "1200px",
              cursor: "pointer",
            }}
            onClick={() => setFlipped(!flipped)}
            aria-label="Flashcard"
          >
            <div
              className={`flip-card-inner rounded-4 shadow-sm ${
                flipped ? "flipped" : ""
              }`}
            >
              <div className="flip-card-front d-flex flex-column justify-content-center align-items-center rounded-4 bg-white text-primary shadow">
                {currentCard?.term}
              </div>
              <div className="flip-card-back d-flex flex-column justify-content-center align-items-center rounded-4 bg-primary text-white shadow">
                {currentCard?.meaning}
              </div>
            </div>
          </div>

          <small className="text-muted fst-italic mt-3 mb-4 user-select-none">
            Nhấn vào thẻ để lật
          </small>

          <div className="d-flex align-items-center gap-4">
            <button
              className="btn btn-outline-primary btn-lg px-4 fw-semibold shadow-sm"
              onClick={prevCard}
              aria-label="Thẻ trước"
            >
              ← Thẻ trước
            </button>

            <div
              className="fs-5 text-primary fw-semibold user-select-none"
              aria-live="polite"
              aria-atomic="true"
            >
              {currentIndex + 1} / {cards.length}
            </div>

            <button
              className="btn btn-primary btn-lg px-4 fw-semibold shadow"
              onClick={nextCard}
              aria-label="Thẻ kế tiếp"
            >
              Thẻ kế tiếp →
            </button>
          </div>
        </>
      ) : (
        <p className="text-muted">Đang tải dữ liệu...</p>
      )}

      <style>{`
        .flip-card-inner {
          position: relative;
          width: 100%;
          height: 100%;
          text-align: center;
          transition: transform 0.7s cubic-bezier(0.4, 0, 0.2, 1);
          transform-style: preserve-3d;
          user-select: none;
        }
        .flip-card-inner.flipped {
          transform: rotateY(180deg);
        }
        .flip-card-front,
        .flip-card-back {
          position: absolute;
          width: 100%;
          height: 100%;
          backface-visibility: hidden;
          border-radius: 1rem;
          display: flex;
          justify-content: center;
          align-items: center;
          padding: 1.5rem;
          box-sizing: border-box;
        }
        .flip-card-front {
          background-color: white;
          color: #0d6efd;
          font-size: 3rem;
          font-weight: 700;
          box-shadow: 0 0.5rem 1rem rgb(13 110 253 / 0.2);
        }
        .flip-card-back {
          background-color: #0d6efd;
          color: white;
          transform: rotateY(180deg);
          font-size: 1.8rem;
          font-weight: 600;
          box-shadow: 0 0.5rem 1rem rgb(0 123 255 / 0.5);
        }
      `}</style>
    </div>
  );
};

export default Practice;
