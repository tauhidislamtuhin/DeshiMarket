// import React from 'react'

// const Login = () => {
//   return (
//     <div>Login</div>
//   )
// }

// export default Login

import { FaArrowLeft } from "react-icons/fa";
import { IoEye } from "react-icons/io5";
import Navbar from "../Navbar/Navbar";
import Footer from "../Footer/Footer";

const Register = () => {
  return (
    <div>
      <Navbar />
      <div className="max-w-container mx-auto">
        <div className="flex pt-24">
          <div className="w-2/4">
            <div className="flex items-center gap-3">
              <p>
                <FaArrowLeft />
              </p>
              <p>Back</p>
            </div>
            <h1 className="font-sans font-bold text-[40px] my-10 text-[#0C0C0D] leading-[24px]">
              Login
            </h1>
            <div className="mt-[20px]">
              <p className="text-[30px] font-sans">Email/Username</p>
              <input
                className="py-[20px] pl-7 pr-[200px] border-[1px] border-[#0C0C0D] rounded-[10px] focus:outline-none"
                type="text"
                placeholder="name415@gmail.com"
              />
            </div>

            <div className="mt-[20px] flex-col flex">
              <p className="text-[30px] font-sans">Password:</p>
              <div className="flex">
                <div className="flex items-center relative gap-3">
                  <input
                    className="py-[20px] pl-7 pr-[200px] border-[1px] border-[#0C0C0D] rounded-[10px] focus:outline-none"
                    type="password"
                    placeholder="#3fgF##%"
                  />
                  <IoEye className="absolute right-5" />
                </div>
              </div>
            </div>
            <button className="text-[24px] hover:bg-[#cdb7b7] w-[79%] mt-[30px] font-sans py-[10px] px-[160px] border-[1px] border-[#0C0C0D] rounded-[10px]">
              Login
            </button>
            <p className="text-[#74767E] text-[14px] mt-[90px] font-medium font-sans pr-[164px]">
              By joining, you agree to the Deshimarket Terms of Service and to
              occasionally receive emails from us. Please read our Privacy
              Policy to learn how we use your personal data.
            </p>
          </div>
          <div className="w-2/4">
            <img src="assets/image1.jpg" alt="assets/image1.jpg" />
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default Register;
