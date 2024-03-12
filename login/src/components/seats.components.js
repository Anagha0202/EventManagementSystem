import React, { useState, useEffect } from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";
import SeatPicker from "react-seat-picker";
import "./Seats.css";



function Seats({ userEmail, setReservationId }) {
  const { eventId } = useParams();
  const [rows, setRows] = useState([]);
  const [selectedSeats, setSelectedSeats] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchSeats = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/seats/${eventId}`);
        const seatsData = response.data.seats;

        const goldSeats = [];
        const silverSeats = [];
        const bronzeSeats = [];

        seatsData.forEach(seat => {
          const seatInfo = {
            id: seat.seatId,
            number: seat.seatId,
            isReserved: !seat.isAvailable,
            price: seat.price,
            name: seat.seatId,
            description: `Seat Class: ${seat.seatClass}, Price: $${seat.price}, Available: ${seat.isAvailable ? "Yes" : "No"}`,
            isSelected: false,
          };

          if (seat.seatClass === "Gold") {
            goldSeats.push(seatInfo);
          } else if (seat.seatClass === "Silver") {
            silverSeats.push(seatInfo);
          } else if (seat.seatClass === "Bronze") {
            bronzeSeats.push(seatInfo);
          }
        });

        setRows([goldSeats, silverSeats, bronzeSeats]);
      } catch (error) {
        console.error('Error fetching seats:', error);
      }
    };

    fetchSeats();
  }, [eventId]);

  const handleSeatSelect = (row, number) => {
    const updatedRows = [...rows];
    const seat = updatedRows[row][number];
    seat.isSelected = !seat.isSelected;
    setRows(updatedRows);

    if (seat.isSelected) {
      setSelectedSeats([...selectedSeats, seat]);
    } else {
      setSelectedSeats(selectedSeats.filter(selected => selected.id !== seat.id));
    }
  };

  const handleConfirmBooking = async () => {
    try {
      const selectedSeatIds = selectedSeats.map((seat) => seat.id);
      const response = await axios.post(`http://localhost:8080/api/reservations/createReservation`, {
        eventId,
        seatIds: selectedSeatIds,
        email: userEmail,
      });
      if (response.data.isSuccessful) {
        console.log("Booking confirmed successfully:", response.data);
        alert("Booking confirmed successfully:", response.data);
        setReservationId(response.data.reservationId); // Set reservationId
        navigate(`/payment`);
      } else {
        alert(response.data.message);
      }
    } catch (error) {
      console.error('Error confirming booking:', error);
    }
  };

  return (
    <div className="seats">
      <div className="screens">
        <h3 className="screen">SCREEN</h3>
      </div>
      <SeatPicker
        addSeatCallback={() => {}}
        removeSeatCallback={() => {}}
        rows={rows}
        alpha
        maxReservableSeats={10}
        visible
        selectedByDefault={false}
        onSeatClick={handleSeatSelect}
        seatStyle={(row, number, isSelected, isReserved) => ({
          backgroundColor: isSelected ? 'blue' : (isReserved ? 'grey' : 'white'), // Change color to grey if seat is not available
        })}
      />
      <div className="seat-data">
        <h2>Seats Data</h2>
        <table>
          <tbody>
            {rows.map((row, rowIndex) => (
              <tr key={rowIndex}>
                {row.map((seat, columnIndex) => (
                  <td key={columnIndex}>
                    {seat && (
                      <button
                        style={{ backgroundColor: seat.isSelected ? 'blue' : 'white' }}
                        onClick={() => handleSeatSelect(rowIndex, columnIndex)}
                        disabled={seat.isReserved} // Disable button for reserved seats
                      >
                        {seat.name}
                      </button>
                    )}
                  </td>
                ))}
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <button onClick={handleConfirmBooking}>Confirm Booking</button>
    </div>
  );
}

export default Seats;
