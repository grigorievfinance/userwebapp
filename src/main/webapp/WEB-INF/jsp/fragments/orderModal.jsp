<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="modalTitle"></h4>
                <button type="button" class="close" data-dismiss="modal" onclick="closeNoty()">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="dateTime" class="col-form-label">DateTime</label>
                        <input class="form-control" id="dateTime" name="dateTime" autocomplete="off"
                               placeholder="DateTime">
                    </div>

                    <div class="form-group">
                        <label for="description" class="col-form-label">Description</label>
                        <input type="text" class="form-control" id="description" name="description"
                               placeholder="Description">
                    </div>

                    <div class="form-group">
                        <label for="price" class="col-form-label">Price</label>
                        <input type="number" class="form-control" id="price" name="price" placeholder="1000">
                    </div>

                    <div class="form-group">
                        <label for="deadline" class="col-form-label">Price</label>
                        <input lass="form-control" id="deadline" name="deadline" autocomplete="off" placeholder="Deadline">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeNoty()">
                    <span class="fa fa-close"></span>
                    Cancel
                </button>
                <button type="button" class="btn btn-primary" onclick="save()">
                    <span class="fa fa-check"></span>
                    Save
                </button>
            </div>
        </div>
    </div>
</div>