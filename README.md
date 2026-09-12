# Novice Runner App 🏃‍♂️

An educational Android mobile application designed to help beginner runners understand heart rate zone training, train safely, and build sustainable running habits.

![Android](https://img.shields.io/badge/Platform-Android-green)
![Java](https://img.shields.io/badge/Language-Java-orange)
![Min SDK](https://img.shields.io/badge/Min%20SDK-API%2026%20(Android%208.0)-blue)
![License](https://img.shields.io/badge/License-Academic-lightgrey)

---

## 📖 Table of Contents

- [About the Project](#about-the-project)
- [Problem Statement](#problem-statement)
- [Features](#features)
- [Screenshots](#screenshots)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [How to Use](#how-to-use)
- [Research Methodology](#research-methodology)
- [Key Findings](#key-findings)
- [Future Enhancements](#future-enhancements)
- [Author](#author)
- [Acknowledgements](#acknowledgements)

---

## 📌 About the Project

**Novice Runner App** is an educational mobile application developed as a final year project for the **BSc (Hons) Computer Science** degree at the **University of East London**.

The app addresses a common problem faced by beginner runners: **intensity perception**. Many novices run too hard, too often, leading to burnout, injury, and ultimately quitting. Unlike commercial running apps (Strava, Nike Run Club) that prioritise GPS tracking, this app focuses on **education** — teaching users how to train using heart rate zones.

---

## ❗ Problem Statement

- Most beginners do not know where or how to start training.
- Many runners train blindly without a proper structure.
- Beginners often imitate what they see on social media without proper education.
- Existing running apps assume prior knowledge of heart rate zones and prioritise tracking over teaching.

---

## ✨ Features

| Feature | Description |
|---|---|
| 🔐 **User Authentication** | Register and login with local storage (SharedPreferences) |
| ❤️ **Heart Rate Zone Calculator** | Calculates Max HR using the Karvonen formula (`220 - age`) and displays all 5 zones |
| 📊 **Colour-Coded Zone Display** | Blue (Zone 1) → Green (Zone 2) → Yellow (Zone 3) → Orange (Zone 4) → Red (Zone 5) |
| 📅 **Weekly Training Plan** | 7-day schedule based on the polarised 80/20 training model |
| 📝 **Manual Run Log** | Record run details with automatic date/time stamping |
| 📚 **Educational Zone Guide** | Detailed explanations of each zone's purpose and perceived effort |
| 🔍 **Searchable Glossary** | 13+ running terms with real-time search functionality |
| 🚪 **Logout** | Secure session clearing and return to login screen |
| 📴 **Offline Functionality** | Works entirely offline with no cloud backend required |

---

## 📸 Screenshots

> *Add your screenshots here. Replace the paths below with your actual image files.*

| Login | Zones | Plan |
|---|---|---|
| ![Login](screenshots/login.png) | ![Zones](screenshots/zones.png) | ![Plan](screenshots/plan.png) |

| Log | Learn | Glossary |
|---|---|---|
| ![Log](screenshots/log.png) | ![Learn](screenshots/learn.png) | ![Glossary](screenshots/glossary.png) |

---

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| **Android Studio** | Integrated Development Environment (IDE) |
| **Java** | Application logic and business rules |
| **XML** | User interface layouts |
| **SharedPreferences** | Local data storage (login sessions, run logs) |
| **Material Design Components** | BottomNavigationView, CardView, Toolbar |
| **ConstraintLayout** | Responsive UI design |
| **RecyclerView** | Efficient list rendering for glossary terms |
| **SearchView** | Real-time filtering of glossary terms |
| **Git & GitHub** | Version control and code hosting |

---

## 📁 Project Structure
