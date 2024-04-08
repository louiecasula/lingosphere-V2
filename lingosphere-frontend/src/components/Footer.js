import React, { useRef } from 'react';
import './Footer.css';

function Footer() {
    const iconRef = useRef(null);

    const handleMouseEnter = () => {
    iconRef.current.style.animationName = 'spin';
    };

    const handleMouseLeave = () => {
    iconRef.current.style.animationName = 'spinBack';
    };

    return (
        <footer>
            <p>Copyright © 2024 The Leuth
                <a href="https://github.com/louiecasula" target="_blank">
                    <i ref={iconRef} onMouseEnter={handleMouseEnter} onMouseLeave={handleMouseLeave} 
                    className="fa fa-github" aria-hidden="true"></i>
                </a>
            </p>
        </footer>
    );
}
export default Footer;