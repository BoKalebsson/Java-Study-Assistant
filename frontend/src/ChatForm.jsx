import { useState } from "react";
import { sendChatMessage } from "./chatApi";

function ChatForm() {
  const [question, setQuestion] = useState("");
  const [expertiseLevel, setExpertiseLevel] = useState("BEGINNER");
  const [answer, setAnswer] = useState(null);
  const [errorMessage, setErrorMessage] = useState(null);
  const [isLoading, setIsLoading] = useState(false);

  async function handleSubmit(event) {
    event.preventDefault();

    setAnswer(null);
    setErrorMessage(null);

    if (!question.trim()) {
      setErrorMessage("Please enter a question.");
      return;
    }

    setIsLoading(true);

    try {
      const responseAnswer = await sendChatMessage(question, expertiseLevel);
      setAnswer(responseAnswer);
    } catch (error) {
      setErrorMessage(error.message);
    } finally {
      setIsLoading(false);
    }
  }

  return (
    <div className="container mt-4">
      <form onSubmit={handleSubmit} className="card p-3 mb-4">
        <div className="mb-3">
          <label className="form-label">Your question</label>
          <textarea
            className="form-control"
            rows="4"
            value={question}
            onChange={(e) => setQuestion(e.target.value)}
          />
        </div>

        <div className="mb-3">
          <label className="form-label">Expertise level</label>
          <select
            className="form-select"
            value={expertiseLevel}
            onChange={(e) => setExpertiseLevel(e.target.value)}
          >
            <option value="BEGINNER">Beginner</option>
            <option value="INTERMEDIATE">Intermediate</option>
            <option value="ADVANCED">Advanced</option>
          </select>
        </div>

        <button type="submit" className="btn btn-primary" disabled={isLoading}>
          {isLoading ? "Loading..." : "Ask"}
        </button>
      </form>

      {errorMessage && <div className="alert alert-danger">{errorMessage}</div>}

      {answer && (
        <div className="card p-3">
          <h5>Answer</h5>
          <p>{answer}</p>
        </div>
      )}
    </div>
  );
}

export default ChatForm;
