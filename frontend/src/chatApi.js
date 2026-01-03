/**
 * Sends a chat message to the Java Study Assistant backend
 * and returns the AI-generated answer.
 */
export async function sendChatMessage(message, expertiseLevel) {
  const response = await fetch("http://localhost:8080/api/chat", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      message: message,
      expertiseLevel: expertiseLevel,
    }),
  });

  // If the response is not OK (4xx or 5xx), handle it as an error.
  if (!response.ok) {
    let errorMessage = "Something went wrong. Please try again.";

    try {
      const errorData = await response.json();
      if (errorData.message) {
        errorMessage = errorData.message;
      }
    } catch {
      // If the error response is not valid JSON, we keep the default error message.
    }

    throw new Error(errorMessage);
  }

  const data = await response.json();
  return data.answer;
}
