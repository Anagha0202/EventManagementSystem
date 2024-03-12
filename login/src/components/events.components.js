import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

export default function EventComponent({ loginCredentialsIsAdmin }) {
  const [events, setEvents] = useState([]);
  const [editableEvent, setEditableEvent] = useState(null);
  const [updatedEvent, setUpdatedEvent] = useState({
    eventName: "",
    eventDateTime: "",
    eventVenue: "",
    eventDesc: "",
    priceGold: "",
    priceSilver: "",
    priceBronze: "",
  });
  const [newEvent, setNewEvent] = useState({
    eventName: "",
    eventDateTime: "",
    eventVenue: "",
    eventDesc: "",
    priceGold: "",
    priceSilver: "",
    priceBronze: "",
  });
  const [creatingEvent, setCreatingEvent] = useState(false);

  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/events`)
      .then((response) => {
        setEvents(response.data);
      })
      .catch((error) => console.log(error));
  }, []);

  const handleDeleteEvent = (eventId) => {
    axios
      .delete(`http://localhost:8080/api/events/removeEvent/${eventId}`)
      .then((response) => {
        const updatedEvents = events.filter(
          (event) => event.eventId !== eventId
        );
        setEvents(updatedEvents);
      })
      .catch((error) => console.log(error));
  };

  const handleUpdateEvent = (event) => {
    if (editableEvent === event) {
      axios
        .put(
          `http://localhost:8080/api/events/updateEvent/${event.eventId}`,
          updatedEvent
        )
        .then((response) => {
          const updatedEvents = events.map((item) =>
            item.eventId === event.eventId ? response.data : item
          );
          setEvents(updatedEvents);
          setEditableEvent(null);
          setUpdatedEvent({
            eventName: "",
            eventDateTime: "",
            eventVenue: "",
            eventDesc: "",
            priceGold: "",
            priceSilver: "",
            priceBronze: "",
          });
        })
        .catch((error) => console.log(error.response.data));
    } else {
      setEditableEvent(event);
      setUpdatedEvent({
        eventName: event.eventName,
        eventDateTime: event.eventDateTime,
        eventVenue: event.eventVenue,
        eventDesc: event.eventDesc,
        priceGold: event.priceGold,
        priceSilver: event.priceSilver,
        priceBronze: event.priceBronze,
      });
    }
  };

  const handleInputChange = (e, eventType) => {
    const { name, value } = e.target;
    if (eventType === "update") {
      setUpdatedEvent((prevState) => ({
        ...prevState,
        [name]: value,
      }));
    } else if (eventType === "create") {
      setNewEvent((prevState) => ({
        ...prevState,
        [name]: value,
      }));
    }
  };

  const handleCreateEvent = () => {
    axios
      .post(`http://localhost:8080/api/events/createEvent`, newEvent)
      .then((response) => {
        setEvents((prevEvents) => [...prevEvents, response.data]);
        setNewEvent({
          eventName: "",
          eventDateTime: "",
          eventVenue: "",
          eventDesc: "",
          priceGold: "",
          priceSilver: "",
          priceBronze: "",
        });
        setCreatingEvent(false);
      })
      .catch((error) => console.log(error.response.data));
  };

  return (
    <div className="container">
      <div className="row">
        <h2 className="text-center m-4">Event Details</h2>
        <div
          className="table-container"
          style={{ maxHeight: "500px", overflowY: "auto" }}
        >
          <table className="table table-bordered">
            <thead>
              <tr>
                <th>ID</th>
                <th>Event Name</th>
                <th>Event Date Time</th>
                <th>Event Venue</th>
                <th>Event Description</th>
                <th>Price Gold</th>
                <th>Price Silver</th>
                <th>Price Bronze</th>
                {loginCredentialsIsAdmin && <th>Actions</th>}
              </tr>
            </thead>
            <tbody>
              {events.map((event, index) => (
                <tr key={index}>
                  <td>{event.eventId}</td>
                  <td>
                    {editableEvent === event ? (
                      <input
                        type="text"
                        name="eventName"
                        value={updatedEvent.eventName}
                        onChange={(e) => handleInputChange(e, "update")}
                      />
                    ) : (
                      <Link to={`/seats/${event.eventId}`}>
                        {event.eventName}
                      </Link>
                    )}
                  </td>
                  <td>
                    {editableEvent === event ? (
                      <input
                        type="text"
                        name="eventDateTime"
                        value={updatedEvent.eventDateTime}
                        onChange={(e) => handleInputChange(e, "update")}
                      />
                    ) : (
                      event.eventDateTime
                    )}
                  </td>
                  <td>
                    {editableEvent === event ? (
                      <input
                        type="text"
                        name="eventVenue"
                        value={updatedEvent.eventVenue}
                        onChange={(e) => handleInputChange(e, "update")}
                      />
                    ) : (
                      event.eventVenue
                    )}
                  </td>
                  <td>
                    {editableEvent === event ? (
                      <input
                        type="text"
                        name="eventDesc"
                        value={updatedEvent.eventDesc}
                        onChange={(e) => handleInputChange(e, "update")}
                      />
                    ) : (
                      event.eventDesc
                    )}
                  </td>
                  <td>
                    {editableEvent === event ? (
                      <input
                        type="text"
                        name="priceGold"
                        value={updatedEvent.priceGold}
                        onChange={(e) => handleInputChange(e, "update")}
                      />
                    ) : (
                      event.priceGold
                    )}
                  </td>
                  <td>
                    {editableEvent === event ? (
                      <input
                        type="text"
                        name="priceSilver"
                        value={updatedEvent.priceSilver}
                        onChange={(e) => handleInputChange(e, "update")}
                      />
                    ) : (
                      event.priceSilver
                    )}
                  </td>
                  <td>
                    {editableEvent === event ? (
                      <input
                        type="text"
                        name="priceBronze"
                        value={updatedEvent.priceBronze}
                        onChange={(e) => handleInputChange(e, "update")}
                      />
                    ) : (
                      event.priceBronze
                    )}
                  </td>
                  {loginCredentialsIsAdmin && (
                    <td>
                      <button
                        className="btn btn-sm btn-info me-2"
                        onClick={() => handleUpdateEvent(event)}
                      >
                        {editableEvent === event ? "Save" : "Update"}
                      </button>
                      <button
                        className="btn btn-sm btn-danger"
                        onClick={() => handleDeleteEvent(event.eventId)}
                      >
                        Delete
                      </button>
                    </td>
                  )}
                </tr>
              ))}
              {creatingEvent && (
                <tr>
                  <td>{/* ID field */}</td>
                  <td>
                    <input
                      type="text"
                      name="eventName"
                      value={newEvent.eventName}
                      onChange={(e) => handleInputChange(e, "create")}
                    />
                  </td>
                  <td>
                    <input
                      type="text"
                      name="eventDateTime"
                      value={newEvent.eventDateTime}
                      onChange={(e) => handleInputChange(e, "create")}
                    />
                  </td>
                  <td>
                    <input
                      type="text"
                      name="eventVenue"
                      value={newEvent.eventVenue}
                      onChange={(e) => handleInputChange(e, "create")}
                    />
                  </td>
                  <td>
                    <input
                      type="text"
                      name="eventDesc"
                      value={newEvent.eventDesc}
                      onChange={(e) => handleInputChange(e, "create")}
                    />
                  </td>
                  <td>
                    <input
                      type="text"
                      name="priceGold"
                      value={newEvent.priceGold}
                      onChange={(e) => handleInputChange(e, "create")}
                    />
                  </td>
                  <td>
                    <input
                      type="text"
                      name="priceSilver"
                      value={newEvent.priceSilver}
                      onChange={(e) => handleInputChange(e, "create")}
                    />
                  </td>
                  <td>
                    <input
                      type="text"
                      name="priceBronze"
                      value={newEvent.priceBronze}
                      onChange={(e) => handleInputChange(e, "create")}
                    />
                  </td>
                  {loginCredentialsIsAdmin && (
                    <td>
                      <button
                        className="btn btn-sm btn-primary"
                        onClick={handleCreateEvent}
                      >
                        Create
                      </button>
                    </td>
                  )}
                </tr>
              )}
            </tbody>
          </table>
        </div>
        <button
          className="btn btn-primary my-2"
          onClick={() => setCreatingEvent(true)}
        >
          Create Event
        </button>
      </div>
    </div>
  );
}
