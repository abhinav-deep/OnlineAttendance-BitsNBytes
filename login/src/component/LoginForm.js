import React,{useState} from 'react'
function LoginForm ({Login,error}){
    const [Name, setName] = useState("");
    const [email, setemail] = useState("");
    const [password, setpassword] = useState("");

    const submitHandler = e =>{
        e.preventDefault();
        let details = {Name,email,password}
        Login(details)
    }
    return (
        <form onSubmit={submitHandler}>
            <div className="inner-Form">
                <h2>Login</h2>
                {(error !== "")? (<div className = "error">{error}</div>):""}
                <div className="group">
                   <label htmlFor="name">Name :</label>
                   <input type="text" id="Name" onChange={(e) => setName(e.target.value)} value={Name}/>
                </div>
                <div className="group">
                   <label htmlFor="email">Email :</label>
                   <input type="email" id="email" onChange={(e) => setemail(e.target.value)} value={email}/>
                </div>
                <div className="group">
                   <label htmlFor="password">Password :</label>
                   <input type="password" id="password" onChange={(e) => setpassword(e.target.value)} value={password}/> 
                </div>
                <input type="submit" value="LOGIN"/>
            </div>
        </form>
    )
}

export default LoginForm