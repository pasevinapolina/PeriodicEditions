<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container modal-container">
    <!-- Modal -->
    <div class="modal fade" id="delEditionModal" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <span class="modal-title">Удаление издания</span>
                </div>

                <div class="modal-body">
                    <p>Вы действительно хотите удалить издание <strong id="editionName"></strong>?</p>
                </div>

                <div class="modal-footer">
                    <form role="form" method="GET" action="PeriodicEdition" name="payDelForm" id="payDelForm">
                        <div class="form-group">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                            <button type="submit" class="btn btn-primary">
                                <span class="glyphicon glyphicon-trash"></span>Удалить</button>
                        </div>

                        <input type="hidden" name="command" value="delete_edition">
                        <input type="hidden" name="delEditionId" id="delEditionId" value="">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="editEditionModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content"></div>
    </div>
    <div class="modal-dialog">
        <div class="modal-content"></div>
    </div>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <span class="modal-title">Редактирование издания</span>
            </div>

            <form action="PeriodicEdition" method="POST" id="editForm" role="form">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="editEditionName">Название</label>
                        <input type="text" class="form-control" id="editEditionName" name="editEditionName" value="">
                    </div>

                    <div class="form-group">
                        <label for="editAuthor">Издательство</label>
                        <input type="text" class="form-control" id="editAuthor" name="editAuthor" value="">
                    </div>

                    <div class="form-group">
                        <label for="editFrequency">Выходит 1 раз в</label>
                        <input type="number" class="form-control" id="editFrequency" name="editFrequency"
                               max="365" min="0" value="">
                    </div>
                </div>

                <div class="modal-footer">
                    <input type="hidden" name="command" value="edit_edition">
                    <input type="hidden" name="editEditionId" id="editEditionId" value="">

                    <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                    <button type="submit" class="btn btn-primary">Сохранить</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="container modal-container">
    <!-- Modal -->
    <div class="modal fade" id="myModal" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <span class="modal-title">Регистрация издания</span>
                </div>

                <div class="modal-body">
                    <form role="form" method="POST" action="PeriodicEdition" name="registerEditionForm">
                        <input type="hidden" name="command" value="register_edition">

                        <div class="form-group">
                            <label for="author">Издательство</label>
                            <input type="text" name="author" class="form-control" id="author" placeholder="Введите издательство">
                        </div>
                        <div class="form-group">
                            <label for="name">Название</label>
                            <input type="text" name="name" class="form-control" id="name" placeholder="Введите название">
                        </div>
                        <div class="form-group">
                            <label for="outFreq">Выходит 1 раз в </label>
                            <input type="number" name="outFreq" min="0" max="365" class="form-control"
                                   id="outFreq" placeholder="Частота выхода в днях">
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">
                            <span class="glyphicon glyphicon-ok"></span>Готово</button>
                    </form>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                </div>

            </div>
        </div>
    </div>
</div>
