import React, { useState } from 'react'
import QRCode from 'qrcode'
function QR() {
    const [text, setText] = useState('')
    const [imgUrl, setimgURL] = useState('')
    const generateQRcode = async () => {
        try {
            const response = await QRCode.toDataURL(text)
            setimgURL(response)
        } catch (error) {
            console.log('error')
        }
    }
    return (
        <div className="Qrcode">
            <div className="group">
                <label htmlFor="name">Enter URL:</label>
                <input type="text" id="Name" onChange={(e) => setText(e.target.value)} />
            </div>
            <button onClick={() => generateQRcode()}>Generate QRCode</button>
            <br />
            <br />
            {imgUrl ? (<img src={imgUrl} alt='img' />) :null }
        </div>
    )
}

export default QR