import React, { useEffect, useState, useRef } from 'react';
import { createStopwatchViewModel, JsStopwatchViewModel } from 'KMPInterop-shared';
import './Stopwatch.css';

const Stopwatch: React.FC = () => {
  const viewModelRef = useRef<JsStopwatchViewModel | null>(null);
  const [formattedTime, setFormattedTime] = useState<string>('0.00');

  useEffect(() => {
    // Create a new viewModel for this effect lifecycle
    const viewModel = createStopwatchViewModel();
    viewModelRef.current = viewModel;
    
    viewModel.observeState((state) => {
      setFormattedTime(state.formattedTime);
    });

    return () => {
      viewModel.onDestroy();
      viewModelRef.current = null;
    };
  }, []);

  const handleStart = () => {
    viewModelRef.current?.onStartClicked();
  };

  const handleStop = () => {
    viewModelRef.current?.onStopClicked();
  };

  return (
    <div className="stopwatch-container">
      <div className="stopwatch-card">
        <h1 className="stopwatch-title">Stopwatch</h1>
        <div className="stopwatch-display">
          <span className="time-value">{formattedTime}</span>
          <span className="time-unit">seconds</span>
        </div>
        <div className="stopwatch-controls">
          <button className="control-button start-button" onClick={handleStart}>
            <svg className="button-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
              <polygon points="5 3 19 12 5 21 5 3"></polygon>
            </svg>
            Start
          </button>
          <button className="control-button stop-button" onClick={handleStop}>
            <svg className="button-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
              <rect x="6" y="6" width="12" height="12"></rect>
            </svg>
            Stop
          </button>
        </div>
      </div>
    </div>
  );
};

export default Stopwatch;

