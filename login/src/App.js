import React, { useState } from 'react'
import LoginForm from './component/LoginForm'
import QR from './component/QR'
import { Link, Route, BrowserRouter as Router, Switch } from 'react-router-dom'
function App() {
  const adminUse = {
    email: "admin@gmail.com",
    password: "admin123"
  }
  const [user, setUser] = useState({ Name: "", email: "" });
  const [error, setError] = useState("");

  const Login = details => {
    console.log(details);
    if (details.email == adminUse.email && details.password == adminUse.password) {
      setUser({
        Name: details.Name,
        email: details.email
      })
    }
    else {
      console.log("Incorrect data")
      setError("Incorrect data");
    }
  }

  const Logout = () => {
    setUser({ Name: "", email: "" });
    console.log("Logout");
  }
  return (
    <div className="App">
      {(user.email !== "") ? (
        <div className="welcome">
          <h2>Welcome ,<span>{user.Name}</span></h2>
          <button onClick={Logout}>Logout</button>
          <QR />
        </div>
      ) : (
          <LoginForm Login={Login} error={error} />
        )}
    </div>
  )

}

export default App;