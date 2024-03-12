import React, { useState } from "react";
import axios from "axios";

export default function Payment({ reservationId }) {
  const [paymentDetails, setPaymentDetails] = useState({
    cardNumber: "",
    cardExpiry: "",
    cardCVV: "",
    cardHolderFullName: "",
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setPaymentDetails((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

 const handleSubmit = () => {
  axios
      .post("http://localhost:8080/api/payment", {
        reservationId: reservationId, 
        ...paymentDetails 
      })

    .then((response) => {
      if (response.data.isSuccessful) {
        console.log("Payment successful");
        
      } else {
        console.error("Payment failed:", response.data.message);
        alert(response.data.message);
        
      }
    })
    .catch((error) => {
      console.error("Payment failed:", error);
      
    });
};



  return (
    <div className="container">
      <h2 className="text-center mt-4">Payment Details</h2>
      <form>
        <div className="mb-3">
          <label htmlFor="cardNumber" className="form-label">
            Card Number
          </label>
          <input
            type="text"
            className="form-control"
            id="cardNumber"
            name="cardNumber"
            value={paymentDetails.cardNumber}
            onChange={handleInputChange}
          />
        </div>
        <div className="row">
          <div className="col-md-6 mb-3">
            <label htmlFor="cardExpiry" className="form-label">
              Card Expiry
            </label>
            <input
              type="text"
              className="form-control"
              id="cardExpiry"
              name="cardExpiry"
              value={paymentDetails.cardExpiry}
              onChange={handleInputChange}
            />
          </div>
          <div className="col-md-6 mb-3">
            <label htmlFor="cardCVV" className="form-label">
              Card CVV
            </label>
            <input
              type="text"
              className="form-control"
              id="cardCVV"
              name="cardCVV"
              value={paymentDetails.cardCVV}
              onChange={handleInputChange}
            />
          </div>
        </div>
        <div className="mb-3">
          <label htmlFor="cardHolderFullName" className="form-label">
            Cardholder Full Name
          </label>
          <input
            type="text"
            className="form-control"
            id="cardHolderFullName"
            name="cardHolderFullName"
            value={paymentDetails.cardHolderFullName}
            onChange={handleInputChange}
          />
        </div>
        <button
          type="button"
          className="btn btn-primary"
          onClick={handleSubmit}
        >
          Submit Payment
        </button>
      </form>
    </div>
  );
}
