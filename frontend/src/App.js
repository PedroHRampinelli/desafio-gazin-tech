import './App.css';
import Nivel from './components/nivel';
import Desenvolvedor from './components/desenvolvedor';
import { BrowserRouter, Routes, Link, Route } from 'react-router-dom';
import { Nav, Container, Navbar, Button } from 'react-bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css';
import ListaDesenvolvedores from './components/lista-desenvolvedores';
import ListaNiveis from './components/lista-niveis';
import './components/style/index.css'

function App() {
  return (
    <Container>
      <div className="text-center mt-5">
        <h1>Desafio Gazin</h1>
      </div>
      <BrowserRouter>
        <Navbar expand="lg" className="bg-body-tertiary">
          <Nav variant='tabs' className="me-auto">
            <Nav.Link as={Link} to="/lista-desenvolvedores"> Desenvolvedores </Nav.Link>
            <Nav.Link as={Link} to="/lista-niveis"> Niveis </Nav.Link>
          </Nav>
        </Navbar>
        <Routes>
          <Route path="/" exact={true} element={<ListaDesenvolvedores />}></Route>
          <Route path="/lista-desenvolvedores" exact={true} element={<ListaDesenvolvedores />}></Route>
          <Route path="/cadastro-nivel" element={<Nivel />}></Route>
          <Route path="/cadastro-desenvolvedor" element={<Desenvolvedor />}></Route>
          <Route path="/lista-niveis" element={<ListaNiveis />}></Route>
        </Routes>

      </BrowserRouter>
    </Container>

  );
}

export default App;
