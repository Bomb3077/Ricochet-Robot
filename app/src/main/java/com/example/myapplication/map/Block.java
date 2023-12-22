package com.example.myapplication.map;

public abstract class Block implements BlockFactory {
    Location location;

    public Block(Location location) {
        this.location = location;
    }
}

class defaultBlock extends Block {
    public defaultBlock(Location location) {
        super(location);
    }

    @Override
    public Block create(Location location) {
        return new defaultBlock(location);
    }
}


class TopBlock extends Block {
    public TopBlock(Location location) {
        super(location);
    }

    @Override
    public Block create(Location location) {
        return new TopBlock(location);
    }
}

class LeftBlock extends Block {
    public LeftBlock(Location location) {
        super(location);
    }

    @Override
    public Block create(Location location) {
        return new LeftBlock(location);
    }
}

class RightBlock extends Block {
    public RightBlock(Location location) {
        super(location);
    }

    @Override
    public Block create(Location location) {
        return new RightBlock(location);
    }
}

class BottomBlock extends Block {
    public BottomBlock(Location location) {
        super(location);
    }

    @Override
    public Block create(Location location) {
        return new BottomBlock(location);
    }
}

class TopRightBlock extends Block {
    public TopRightBlock(Location location) {
        super(location);
    }

    @Override
    public Block create(Location location) {
        return new TopRightBlock(location);
    }
}

class TopLeftBlock extends Block {

    public TopLeftBlock(Location location) {
        super(location);
    }

    @Override
    public Block create(Location location) {
        return new TopLeftBlock(location);
    }
}

class BottomRightBlock extends Block {


    public BottomRightBlock(Location location) {
        super(location);
    }

    @Override
    public Block create(Location location) {
        return new BottomRightBlock(location);
    }
}

class BottomLeftBlock extends Block {

    public BottomLeftBlock(Location location) {
        super(location);
    }

    @Override
    public Block create(Location location) {
        return new BottomLeftBlock(location);
    }
}
