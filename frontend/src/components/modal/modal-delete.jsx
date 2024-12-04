import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';

function ModalDelete(props) {
  return (
    <>
      <Modal
        show={true}
        backdrop="static"
        keyboard={false}
        centered
        size="sm"
      >
        <Modal.Header closeButton>
          <Modal.Title>
            {props.modalType === "devs" ? "Excluir Desenvolvedor" : "Excluir Nível"}
          </Modal.Title>
        </Modal.Header>
        
        <Modal.Body>
          <p>
            {props.modalType === "devs" ? "Deseja excluir um desenvolvedor?" : "Deseja excluir um nível?"}
          </p>
          <p className="text-muted">
            Esta ação não pode ser desfeita.
          </p>
        </Modal.Body>
        
        <Modal.Footer>
          <Button variant="secondary" onClick={props.onHide}>
            Cancelar
          </Button>
          <Button variant="danger" onClick={props.onDelete}>
            Sim, Excluir
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default ModalDelete;
